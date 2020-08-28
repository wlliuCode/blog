package com.wlliu.blog.service.user.service;

import com.wlliu.blog.base.entity.entity.User;
import com.wlliu.blog.service.user.entity.vo.RegisterVo;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    String getMessage();

    void register(RegisterVo registerVo);
}
