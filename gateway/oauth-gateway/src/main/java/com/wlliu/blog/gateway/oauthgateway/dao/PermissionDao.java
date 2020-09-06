package com.wlliu.blog.gateway.oauthgateway.dao;

import com.wlliu.blog.gateway.oauthgateway.entity.SysPermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionDao {

    @Select("select * from sys_permission")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.wlliu.blog.gateway.oauthgateway.dao.RoleDao.findByPid"))
    })
    List<SysPermission> findAllPermissionWithRoles();


    @Select("select * from sys_permission")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleNames", column = "id", javaType = List.class,
                    many = @Many(select = "com.wlliu.blog.gateway.oauthgateway.dao.RoleDao.findRoleNamesByPid"))
    })
    List<SysPermission> findAllPermissionWithRoleNames();
}
