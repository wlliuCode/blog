package com.wlliu.blog.service.user.service;

import com.wlliu.blog.base.entity.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    String getMessage();
}
