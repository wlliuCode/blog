package com.wlliu.blog.gateway.oauthauth.service;


import com.wlliu.blog.gateway.oauthauth.domain.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> findAllPermissionWithRoles();
}
