package com.wlliu.blog.service.sms.controller;

import com.aliyuncs.exceptions.ClientException;
import com.wlliu.blog.base.result.result.Result;
import com.wlliu.blog.base.utils.utils.FormUtils;
import com.wlliu.blog.base.utils.utils.RandomUtils;
import com.wlliu.blog.service.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/sms")
public class SmsController {

    @Autowired
    SmsService smsService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("send/{mobile}")
    public Result getLoginCode(@PathVariable("mobile") String mobile) throws ClientException {

        if (StringUtils.isEmpty(mobile) || !FormUtils.isMobile(mobile)){
            return Result.error().message("手机号不正确");
        }

        String sixBitCode = RandomUtils.getSixBitRandom();

        //smsService.send(mobile,sixBitCode);

        redisTemplate.opsForValue().set(mobile,sixBitCode,5, TimeUnit.MINUTES);

        return Result.ok().message("success");
    }
}
