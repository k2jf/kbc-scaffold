package com.k2data.kbc.api;

/**
 * Created by zhanghao on 2019/3/11.
 * 用户权限异常
 */
public class KbcPermException extends KbcException {

    public KbcPermException() {
        super("权限不足");
    }

    @Override
    protected int getHttpCode() {
        return 403;
    }
}
