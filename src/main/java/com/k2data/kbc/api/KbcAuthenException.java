package com.k2data.kbc.api;

/**
 * Created by zhanghao on 2019/3/11.
 * 用户身份认证异常
 */
public class KbcAuthenException extends KbcException {

    public KbcAuthenException() {
        super("请重新登录");
    }

    @Override
    protected int getHttpCode() {
        return 401;
    }
}
