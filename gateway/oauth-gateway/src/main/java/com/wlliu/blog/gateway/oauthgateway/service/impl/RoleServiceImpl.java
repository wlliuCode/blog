package com.wlliu.blog.gateway.oauthgateway.service.impl;

import com.wlliu.blog.gateway.oauthgateway.dao.RoleDao;
import com.wlliu.blog.gateway.oauthgateway.entity.SysRole;
import com.wlliu.blog.gateway.oauthgateway.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SysRole> findByUid(Integer uid) {
        return roleDao.findByUid(uid);
    }

    @Override
    public List<SysRole> findByPid(Integer pid) {
        return roleDao.findByPid(pid);
    }

    @Override
    public List<String> findRoleNamesByPid(Integer pid) {
        return roleDao.findRoleNamesByPid(pid);
    }

    @Override
    public List<String> findByPermissionUrl(String permissionUrl) {
        return roleDao.findByPermissionUrl(permissionUrl);
    }
}
