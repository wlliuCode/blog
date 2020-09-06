package com.wlliu.blog.gateway.oauthauth.domain;

import lombok.Data;

import java.util.List;

@Data
public class SysPermission {
    private Integer id;
    private String permissionName;
    private String permissionUrl;
    private Integer parentId;
    private List<SysRole> roles;
}
