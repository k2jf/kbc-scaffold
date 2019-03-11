package com.k2data.kbc.kmx;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.mitre.dsmiley.httpproxy.URITemplateProxyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * Created by zhanghao on 2019/3/8.
 * https://github.com/mitre/HTTP-Proxy-Servlet
 */
@Configuration
public class KmxProxyConfiguration {

    @Value("${kbc.kmx.url}")
    private String kmxUrl;

    @Value("${kbc.kmx.k2key}")
    private String kmxK2Key;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new KmxProxyServlet(), "/kmx/*");
        servletRegistrationBean.addInitParameter("targetHost", kmxUrl );
        servletRegistrationBean.addInitParameter("targetUri", "");
        servletRegistrationBean.addInitParameter("k2key", kmxK2Key);
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, "true");
        return servletRegistrationBean;
    }

//    /**
//     * 如果发现servlet无法获取到request内容，可尝试下面的解决方法
//     * https://github.com/mitre/HTTP-Proxy-Servlet/issues/83#issuecomment-307216795
//     *
//     * @param filter
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean registration(HiddenHttpMethodFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }

}