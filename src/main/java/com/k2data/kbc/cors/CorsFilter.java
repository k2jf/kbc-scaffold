package com.k2data.kbc.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanghao on 2019/3/12.
 * TODO: @CrossOrigin注解未能生效(https://spring.io/guides/gs/rest-service-cors/)，暂时使用filter方案
 * https://www.cnblogs.com/yoyotl/p/7090996.html
 */
@Component
@Configuration
public class CorsFilter implements Filter {

    @Value("${kbc.cors.allow.origin}")
    private String corsAllowOrigin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse servletResponse = (HttpServletResponse) res;

        //Header名称不区分大小写
        //servletResponse.setHeader("Access-Control-Allow-Origin", "*"); //客户端withCredential时不被允许使用"*"
        servletResponse.getHeader("Access-Control-Allow-Origin");
        if(corsAllowOrigin!=null &&!corsAllowOrigin.isEmpty()) {
            servletResponse.setHeader("Access-Control-Allow-Origin", corsAllowOrigin);
        }else{
            String requestOriginHeader = ((HttpServletRequest)req).getHeader("Origin");
            servletResponse.setHeader("Access-Control-Allow-Origin", requestOriginHeader);
        }
        servletResponse.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, PUT, DELETE");
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Access-Control-Max-Age", "3600");
        servletResponse.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header, Authorization");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}