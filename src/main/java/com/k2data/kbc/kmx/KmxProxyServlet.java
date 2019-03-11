package com.k2data.kbc.kmx;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.message.BasicHeader;
import org.mitre.dsmiley.httpproxy.ProxyServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanghao on 2019/3/11.
 */
public class KmxProxyServlet extends ProxyServlet {

    @Override
    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        servletRequest.setAttribute(ATTR_TARGET_HOST, new HttpHost("10.12.20.36", 28091));
//        servletRequest.setAttribute(ATTR_TARGET_URI, "test");
        super.service(servletRequest, servletResponse);
    }

    @Override
    protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {
        super.copyRequestHeaders(servletRequest, proxyRequest);
        proxyRequest.addHeader(new BasicHeader("K2_KEY", getConfigParam("k2key")));
    }
}
