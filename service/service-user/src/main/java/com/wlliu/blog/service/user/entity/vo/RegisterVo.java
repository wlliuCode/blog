package com.wlliu.blog.service.user.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 6319726740710530106L;
    private String username;
    private String mobile;
    private String password;
    private String code;
}
