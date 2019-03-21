package com.k2data.kbc.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghao on 2019/3/11.
 * 对Rest响应内容的封装
 */
public class KbcResponse {

    public static KbcResponse SUCCESS = new KbcResponse("success"); //快捷方式（KbcResponse.SUCCESS）

    private String message = ""; //一般在非200情况下才返回消息

    private Map<String, Object> body = new HashMap<>(); //用于存放实际的业务数据

    public KbcResponse() {
    }

    public KbcResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getBody() {
        return body;
    }

}
