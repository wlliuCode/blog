package com.wlliu.blog.service.user.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 3281834413397516659L;
    private String mobile;
    private String password;
}