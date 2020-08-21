package com.wlliu.blog.service.user.controller;

import com.github.pagehelper.PageInfo;
import com.wlliu.blog.base.entity.entity.User;
import com.wlliu.blog.service.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public PageInfo<User> getUserList(@Param("pageNum") int pageNum,
                                      @Param("pageSize") int pageSize) {
        return userService.getUserList(pageNum, pageSize);
    }
    @GetMapping("message")
    public String getMessage() {
        return userService.getMessage();
    }
}
