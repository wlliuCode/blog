package com.wlliu.blog.service.user.service;

import com.wlliu.blog.base.entity.entity.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RocketMQMessageListener(consumerGroup = "mq-user-group",topic = "user-topic")
public class SmsService implements RocketMQListener<List<User>> {
    @Override
    public void onMessage(List<User> users) {
        System.out.println(users.toString());
    }
}
