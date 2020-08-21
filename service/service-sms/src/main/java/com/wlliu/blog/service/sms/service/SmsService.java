package com.wlliu.blog.service.sms.service;

import com.aliyuncs.exceptions.ClientException;

public interface SmsService {
    void send(String mobile, String sixBitCode) throws ClientException;
}
