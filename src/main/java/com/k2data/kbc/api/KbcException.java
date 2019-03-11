package com.k2data.kbc.api;

/**
 * Created by zhanghao on 2019/3/11.
 * 应用的异常基类
 */
public abstract class KbcException extends Exception {

    public KbcException() {
    }

    public KbcException(String message) {
        super(message);
    }

    abstract protected int getHttpCode();
}
