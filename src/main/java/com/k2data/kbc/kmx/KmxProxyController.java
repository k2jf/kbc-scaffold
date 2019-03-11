package com.k2data.kbc.kmx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @deprecated 改为使用第三方proxy实现
 *
 * Created by zhanghao on 2019/3/8.
 * 用于转发来自前端的KMX请求
 */
@RestController
public class KmxProxyController {

//    @Value("${kbc.kmx.url}")
//    private String kmxUrl;
//
//    @Value("${kbc.kmx.k2key}")
//    private String kmxK2Key;
//
//    /**
//     * 转发来自前端的KMX请求
//     * TODO: ip地址是否需要配置并解析？
//     *
//     * @param path kmx访问路径，不含地址
//     * @return
//     */
//    @RequestMapping(value = "kmx/{path}")
//    public String kmx(@PathVariable String path) {
//        return "access " + kmxUrl + "/" + path;
//    }
}
