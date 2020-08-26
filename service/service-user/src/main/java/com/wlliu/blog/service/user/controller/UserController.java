package com.wlliu.blog.service.user.controller;

import com.wlliu.blog.base.entity.entity.User;
import com.wlliu.blog.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getUserList() {
        return userService.getUserList();
    }
    @GetMapping("message")
    public String getMessage() {
        return userService.getMessage();
    }
}
