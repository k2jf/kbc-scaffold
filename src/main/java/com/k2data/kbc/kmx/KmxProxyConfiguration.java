package com.k2data.kbc.kmx;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghao on 2019/3/8.
 * 将代理servlet注册到环境（省去在web.xml里注册），注册时配置了若干参数，这些参数在application.properties中可配置。
 *
 * 基于第三方类库实现
 * https://github.com/mitre/HTTP-Proxy-Servlet
 */
@Configuration
public class KmxProxyConfiguration {

    @Value("${kbc.kmx.host}")
    private String kmxHost;

    @Value("${kbc.kmx.k2key}")
    private String kmxK2Key;

    @Value("${kbc.kmx.port.data.service.v2}")
    private String kmxPortDataServiceV2;

    @Value("${kbc.kmx.port.data.service.v3}")
    private String kmxPortDataServiceV3;

    @Value("${kbc.kmx.port.batch.rest}")
    private String kmxPortBatchRest;

    @Value("${kbc.kmx.port.object.rest}")
    private String kmxPortObjectRest;

    @Value("${kbc.kmx.port.file.rest}")
    private String kmxPortFileRest;

    @Value("${kbc.kmx.port.pas.services}")
    private String kmxPortPasServices;

    @Value("${kbc.kmx.port.ecf.rest}")
    private String kmxPortEcfRest;

    @Value("${kbc.kmx.port.meter.v1}")
    private String kmxPortMeterV1;

    @Value("${kbc.kmx.port.auth.service}")
    private String kmxPortAuthService;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        //直接读取application.properties为map类型有问题
        //使用代码构造map传递给KmxProxyServlet
        Map<String, Integer> portMap = new HashMap();
        portMap.put("data-service/v2", Integer.parseInt(kmxPortDataServiceV2));
        portMap.put("data-service/v3", Integer.parseInt(kmxPortDataServiceV3));
        portMap.put("batch-rest", Integer.parseInt(kmxPortBatchRest));
        portMap.put("object-rest", Integer.parseInt(kmxPortObjectRest));
        portMap.put("file-rest", Integer.parseInt(kmxPortFileRest));
        portMap.put("pas-services",Integer.parseInt( kmxPortPasServices));
        portMap.put("ecf-rest", Integer.parseInt(kmxPortEcfRest));
        portMap.put("meter/v1", Integer.parseInt(kmxPortMeterV1));
        portMap.put("auth-service", Integer.parseInt(kmxPortAuthService));

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new KmxProxyServlet(portMap), "/kmx/*");
        servletRegistrationBean.addInitParameter("targetHost", kmxHost);
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