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

        //处理逻辑放在chain.doFilter之后进行
        chain.doFilter(req, res);

        HttpServletResponse response = (HttpServletResponse) res;

        //加条件判断以免重复添加同名header（考虑kmx反向代理的例子）
        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.setHeader("Access-Control-Allow-Methods",
                    "POST, GET, OPTIONS, PUT, DELETE");
        }
        if (response.getHeader("Access-Control-Allow-Credentials") == null) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
        if (response.getHeader("Access-Control-Max-Age") == null) {
            response.setHeader("Access-Control-Max-Age", "3600");
        }
        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.setHeader("Access-Control-Allow-Headers",
                    "Content-Type, x-requested-with, X-Custom-Header, Authorization");
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}