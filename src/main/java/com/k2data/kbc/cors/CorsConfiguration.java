package com.k2data.kbc.cors;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 支持跨域请求的配置项
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")
                .allowedHeaders("Accept", "Content-Type", "Origin", "Authorization", "X-Auth-Token")
                .exposedHeaders("X-Auth-Token", "Authorization")
                .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS");
    }

    @Bean
    public FilterRegistrationBean getMyFilter() {
        MyFilter filter = new MyFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    class MyFilter implements Filter {

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

}