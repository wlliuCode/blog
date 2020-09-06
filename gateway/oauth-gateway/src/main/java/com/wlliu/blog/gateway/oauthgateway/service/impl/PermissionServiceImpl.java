package com.wlliu.blog.gateway.oauthgateway.service.impl;

import com.wlliu.blog.gateway.oauthgateway.domain.SysPermission;
import com.wlliu.blog.gateway.oauthgateway.mapper.PermissionMapper;
import com.wlliu.blog.gateway.oauthgateway.service.PermissionService;
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
