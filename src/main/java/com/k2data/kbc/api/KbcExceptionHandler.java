package com.k2data.kbc.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanghao on 2019/3/12.
 * 全局异常处理配置
 */
@ControllerAdvice
public class KbcExceptionHandler {

    @ExceptionHandler(KbcException.class)
    @ResponseBody
    public KbcResponse kbcExceptionHandler(HttpServletResponse httpResp, KbcException e) {
        KbcResponse kbcResp = new KbcResponse(e.getMessage());
        httpResp.setStatus(e.getHttpCode());
        return kbcResp;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public KbcResponse generalException(HttpServletResponse httpResp, Exception e) {
        KbcResponse kbcResp = new KbcResponse(e.getMessage());
        httpResp.setStatus(500); //Internal
        return kbcResp;
    }
}
