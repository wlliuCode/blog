package com.wlliu.blog.gateway.oauthauth.exception;


import com.wlliu.blog.gateway.oauthauth.result.Result;
import com.wlliu.blog.gateway.oauthauth.result.ResultCodeEnum;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 * Created by macro on 2020/7/17.
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public Result handleOauth2(OAuth2Exception e) {
        return Result.setResult(ResultCodeEnum.OAUTH_ERROR);
    }
}
