package com.wlliu.blog.service.user.controller;

import com.wlliu.blog.base.service.entity.User;
import com.wlliu.blog.base.service.exception.GlobalException;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.base.service.result.ResultCodeEnum;
import com.wlliu.blog.base.utils.utils.JwtInfo;
import com.wlliu.blog.base.utils.utils.JwtUtils;
import com.wlliu.blog.service.user.dao.UserDao;
import com.wlliu.blog.service.user.entity.vo.LoginVo;
import com.wlliu.blog.service.user.entity.vo.RegisterVo;
import com.wlliu.blog.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        String token = userService.login(loginVo);
        return Result.ok().data("token", token);
    }


    @GetMapping("get-login-info")
    public Result getLoginInfo(HttpServletRequest request) {
        try {
            JwtInfo jwtInfo = JwtUtils.getUserIdByJwtToken(request);
            return Result.ok().data("userInfo", jwtInfo);
        } catch (Exception e) {
            throw new GlobalException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @PostMapping("{id}")
    public Result getUserById(@PathVariable("id") String id){
        return Result.ok().data("user", userService.getUserById(id));
    }

    @GetMapping("message")
    public String getMessage() {
        return userService.getMessage();
    }
}
