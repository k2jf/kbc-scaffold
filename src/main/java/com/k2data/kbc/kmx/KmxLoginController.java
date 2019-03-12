package com.k2data.kbc.kmx;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcException;
import com.k2data.kbc.api.KbcResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by zhanghao on 2019/3/11.
 */
@Api("KMX用户验证接口")
@Configuration
@RestController
public class KmxLoginController {

    private static final Logger logger = LoggerFactory.getLogger(KmxLoginController.class);

    @Value("${kbc.kmx.host}")
    private String kmxHost;

    @Value("${kbc.kmx.port.auth.service}")
    private String kmxPortAuthService;


    @ApiOperation("验证KMX用户密码")
    @PostMapping(value = "kmx_login")
    KbcResponse login(@RequestBody LoginRequest req) throws KbcException {
        if (req.getUsername() == null || req.getUsername().isEmpty()) {
            throw new KbcBizException("请输入用户名");
        }
        if (req.getPassword() == null || req.getPassword().isEmpty()) {
            throw new KbcBizException("请输入密码");
        }

        boolean valid = false;
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String kmxUrl = "http://" + kmxHost + ":" + kmxPortAuthService + "/auth-service/v1/health/check_login";
        logger.info("Authenticating with " + kmxUrl);
        HttpGet httpGet = new HttpGet(kmxUrl);
        String basicAuthToken = makeKmxBasicAuthToken(req.getUsername(), req.getPassword());
        httpGet.addHeader(new BasicHeader("Authorization", basicAuthToken));
        try {
            HttpResponse resp = client.execute(httpGet);
            valid = resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
            String body = EntityUtils.toString(resp.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
            }
        }

        //kmx的basic auth调用有问题，解决中
        //暂时放行所有请求
//        if (!valid) {
//            throw new KbcBizException("验证未通过");
//        }

        return KbcResponse.SUCCESS; //dummy implementation
    }

    /**
     * 将用户名和密码生成basic auth token
     * 算法逻辑见KMX 2.1使用手册《BASIC AUTH鉴权》
     *
     * @param username
     * @param password
     * @return
     */
    private String makeKmxBasicAuthToken(String username, String password) {
        String str = username + ":" + password;
        String hash = Base64.getEncoder().encodeToString(str.getBytes());
        return "Basic " + hash;
    }

}

//TODO: 能否省去这个类
class LoginRequest {
    public LoginRequest() {
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
