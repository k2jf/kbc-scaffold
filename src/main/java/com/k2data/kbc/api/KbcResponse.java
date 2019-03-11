package com.k2data.kbc.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghao on 2019/3/11.
 * 对Rest响应内容的封装
 */
public class KbcResponse {

    public static KbcResponse SUCCESS = new KbcResponse("success");

    private String message = "";
    private Map<String, Object> body = new HashMap<>();

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
