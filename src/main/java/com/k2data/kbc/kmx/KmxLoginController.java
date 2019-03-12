package com.k2data.kbc.kmx;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcException;
import com.k2data.kbc.api.KbcResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.tomcat.util.http.fileupload.util.Closeable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by zhanghao on 2019/3/11.
 */
@Configuration
@RestController
public class KmxLoginController {

    @Value("${kbc.kmx.host}")
    private String kmxHost;

    @Value("${kbc.kmx.port.auth.service}")
    private String kmxPortAuthService;


    @RequestMapping(value = "kmx_login", method = RequestMethod.POST)
    KbcResponse login(@RequestBody LoginRequest req) throws KbcException {
        if (req.username == null || req.username.isEmpty()) {
            throw new KbcBizException("请输入用户名");
        }
        if (req.password == null || req.password.isEmpty()) {
            throw new KbcBizException("请输入密码");
        }

        boolean valid = false;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(kmxHost + ":" + kmxPortAuthService + "/auth-service/v1/health/check_login");
        get.addHeader(new BasicHeader("Authorization", "Basic "));
        try {
            HttpResponse resp = client.execute(get);
            valid = resp.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
        } finally {
            try {
                client.close();
            } catch (IOException e) {
            }
        }

        if (!valid) {
            throw new KbcBizException("验证未通过");
        }

        return KbcResponse.SUCCESS; //dummy implementation
    }

    //TODO: 能否省去这个类
    static class LoginRequest {
        public LoginRequest() {
        }

        protected String username;
        protected String password;
    }
}
