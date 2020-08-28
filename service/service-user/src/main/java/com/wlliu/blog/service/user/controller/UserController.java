package com.wlliu.blog.service.user.controller;

import com.wlliu.blog.base.entity.entity.User;
import com.wlliu.blog.base.result.result.Result;
import com.wlliu.blog.service.user.entity.vo.RegisterVo;
import com.wlliu.blog.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("register")
    public Result userRegister(@RequestBody RegisterVo registerVo) {
        userService.register(registerVo);
        return Result.ok().message("注册成功");
    }

    @GetMapping("message")
    public String getMessage() {
        return userService.getMessage();
    }
}
