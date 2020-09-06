package com.wlliu.blog.gateway.oauthgateway.service;


import com.wlliu.blog.gateway.oauthgateway.domain.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> findAllPermissionWithRoles();
}
