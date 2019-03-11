package com.k2data.kbc.api;

/**
 * Created by zhanghao on 2019/3/11.
 * 业务逻辑异常
 */
public class KbcBizException extends KbcException {

    public KbcBizException(String message) {
        super(message);
    }

    @Override
    protected int getHttpCode() {
        return 400;
    }
}
