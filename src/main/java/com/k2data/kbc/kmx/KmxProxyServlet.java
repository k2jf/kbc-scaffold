package com.k2data.kbc.kmx;

import org.apache.catalina.connector.ResponseFacade;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.message.BasicHeader;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhanghao on 2019/3/11.
 * 将前端发来的http请求转发给kmx环境（/kmx/*），其中做了如下处理：
 * 1、添加K2_KEY到header
 * 2、根据application.properties里的配置，自动查找正确的端口号
 * 3、QueryString保持不变
 */
public class KmxProxyServlet extends ProxyServlet {

    /**
     * 用于根据url确定kmx端口号的映射表
     * (data_service/v2, 8081)
     * (data_service/v3, 8082)
     * ...
     */
    private Map<String, Integer> portMap;

    public KmxProxyServlet(Map<String, Integer> portMap) {
        this.portMap = portMap;
    }

    @Override
    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        //1、会被添加到targetUrl
        //2、为正确处理port，必须在处理port前设置，否则会产生"null"在targetUrl里
        servletRequest.setAttribute(ATTR_TARGET_URI, "");

        //查找kmx对应的端口号
        int port = 0;
        String kmxUrl = rewriteUrlFromRequest(servletRequest); //例如：/auth-service/v1/users?page=1&size=5
        for (String prefix : portMap.keySet()) {
            if (kmxUrl.startsWith(prefix) || kmxUrl.startsWith("/" + prefix)) {
                port = portMap.get(prefix);
                break;
            }
        }
        if (port == 0) {
            throw new ServletException("Failed to find kmx port: " + kmxUrl);
        }

        String kmxHost = getConfigParam("targetHost");
        if (kmxHost == null || kmxHost.length() == 0) {
            throw new ServletException("Kmx host must not be empty");
        }
        if (kmxHost.indexOf(":") > 0) {
            throw new ServletException("Kmx host must not include port number: " + kmxHost);
        }
        servletRequest.setAttribute(ATTR_TARGET_HOST, new HttpHost(kmxHost, port));

        super.service(servletRequest, servletResponse);

        //KMX接口会返回CORS headers
        //在配置了CORS filter的环境下，会导致重复添加header，且在filter里不容易解决
        //在这里重新配置一次header可以解决重复header的问题
        //这段代码会在doFilter()之后执行
        //servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        String corsAllowOrigin = getConfigParam("corsAllowOrigin");
        if (corsAllowOrigin != null && !corsAllowOrigin.isEmpty()) {
            servletResponse.setHeader("Access-Control-Allow-Origin", corsAllowOrigin);
        } else {
            String requestOriginHeader = servletRequest.getHeader("Origin");
            servletResponse.setHeader("Access-Control-Allow-Origin", requestOriginHeader);
        }
        servletResponse.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, PUT, DELETE");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header, Authorization");

    }

    @Override
    protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {
        super.copyRequestHeaders(servletRequest, proxyRequest);
        proxyRequest.addHeader(new BasicHeader("K2_KEY", getConfigParam("k2key")));
    }
}
