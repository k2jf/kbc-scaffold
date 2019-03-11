package com.k2data.kbc.kmx;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcException;
import com.k2data.kbc.api.KbcResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhanghao on 2019/3/11.
 */
@RestController
public class KmxLoginController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    KbcResponse login(@RequestBody LoginRequest req) throws KbcException {
        if (req.username == null || req.username.isEmpty()) {
            throw new KbcBizException("请输入用户名");
        }
        if (req.password == null || req.password.isEmpty()) {
            throw new KbcBizException("请输入密码");
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
