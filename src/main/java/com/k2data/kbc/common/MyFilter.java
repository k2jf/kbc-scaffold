package com.k2data.kbc.common;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyFilter implements Filter {

    protected String allowMethods;
    protected String allowOrigin;
    private static final String DEFAULT_ALLOW_HEADERS = "Content-Type,X-Requested-With";
    /**
     * support custom headers
     */
    protected String allowHeaders;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.allowMethods = fConfig.getInitParameter("Access-Control-Allow-Methods");
        this.allowOrigin = fConfig.getInitParameter("Access-Control-Allow-Origin");
        this.allowHeaders = fConfig.getInitParameter("Access-Control-Allow-Headers");
        if (this.allowHeaders == null) {
            this.allowHeaders = DEFAULT_ALLOW_HEADERS;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse hResponse = (HttpServletResponse) response;
        hResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        hResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("origin"));
        hResponse.setHeader("Access-Control-Allow-Credentials", "true");
        hResponse.setHeader("Access-Control-Allow-Headers", this.allowHeaders);
        hResponse.setCharacterEncoding("UTF-8");
        chain.doFilter(request, hResponse);

    }

    @Override
    public void destroy() {
        this.allowMethods = null;
        this.allowOrigin = null;
        this.allowHeaders = null;
    }
}