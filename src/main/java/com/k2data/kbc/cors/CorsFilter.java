package com.k2data.kbc.cors;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanghao on 2019/3/12.
 * TODO: @CrossOrigin注解未能生效(https://spring.io/guides/gs/rest-service-cors/)，暂时使用filter方案
 * https://www.cnblogs.com/yoyotl/p/7090996.html
 */
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse servletResponse = (HttpServletResponse) res;

        //servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        servletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");//FIXME: 暂时方案
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