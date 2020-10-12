package com.wlliu.blog.gateway.oauthauth.controller;

import com.wlliu.blog.gateway.oauthauth.entity.Oauth2TokenDto;
import com.wlliu.blog.gateway.oauthauth.result.Result;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        /*OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();*/
        ResponseEntity<OAuth2AccessToken> tokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        OAuth2AccessToken auth2AccessToken = tokenResponseEntity.getBody();
        return Result.ok().data("auth2AccessToken",auth2AccessToken);
    }

    @GetMapping("code")
    public void getCode(@Param("code") String code) {
        System.out.println(code);
    }
}
