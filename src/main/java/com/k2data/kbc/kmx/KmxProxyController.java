package com.k2data.kbc.kmx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanghao on 2019/3/8.
 * 用于转发来自前端的KMX请求
 */
@RestController
public class KmxProxyController {

    @Value("${kbc.kmx.url}")
    private String kmxUrl;

    @RequestMapping(value = "kmx/{path}")
    public String kmx(@PathVariable String path) {
        return "access " + kmxUrl + "/" + path;
    }
}
