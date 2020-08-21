package com.wlliu.blog.service.user.service;

import com.github.pagehelper.PageInfo;
import com.wlliu.blog.base.entity.entity.User;

public interface UserService {
    PageInfo<User> getUserList(int pageNum, int pageSize);

    String getMessage();
}
