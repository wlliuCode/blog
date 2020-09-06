package com.wlliu.blog.gateway.oauthgateway.service.impl;

import com.wlliu.blog.gateway.oauthgateway.entity.SysPermission;
import com.wlliu.blog.gateway.oauthgateway.dao.PermissionDao;
import com.wlliu.blog.gateway.oauthgateway.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<SysPermission> findAllPermissionWithRoles() {
        return permissionDao.findAllPermissionWithRoles();
    }

    @Override
    public List<SysPermission> findAllPermissionWithRoleNames() {
        return permissionDao.findAllPermissionWithRoleNames();
    }
}
