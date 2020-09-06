package com.wlliu.blog.service.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wlliu.blog.base.service.exception.BlogException;
import com.wlliu.blog.base.service.result.ResultCodeEnum;
import com.wlliu.blog.service.sms.entity.SmsProperties;
import com.wlliu.blog.service.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    SmsProperties smsProperties;

    @Override
    public void send(String mobile, String sixBitCode) throws ClientException {

        DefaultProfile profile = DefaultProfile.getProfile(
                smsProperties.getRegionId(),
                smsProperties.getKeyId(),
                smsProperties.getKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());

        Map<String, String> codeJson = new HashMap<>();
        codeJson.put("code", sixBitCode);
        request.putQueryParameter("TemplateParam", JSON.toJSON(codeJson).toString());

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());
        String data = response.getData();
        HashMap<String, String> map = JSON.parseObject(data, HashMap.class);
        String code = map.get("Code");
        String message = map.get("Message");

        if ("isv.BUSINESS_LIMIT_CONTROL".equals(message)) {
            log.error("发送短信过于频繁：code={},message={}", code, message);
            throw new BlogException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

        if (!"OK".equals(code)) {
            log.error("发送短信失败：code={},message={}", code, message);
            throw new BlogException(ResultCodeEnum.SMS_SEND_ERROR);
        }


    }
}
