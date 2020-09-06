package com.wlliu.blog.gateway.oauthauth.service.impl;


import com.wlliu.blog.gateway.oauthauth.domain.SysPermission;
import com.wlliu.blog.gateway.oauthauth.mapper.PermissionMapper;
import com.wlliu.blog.gateway.oauthauth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<SysPermission> findAllPermissionWithRoles() {
        return permissionMapper.findAllPermissionWithRoles();
    }
}
