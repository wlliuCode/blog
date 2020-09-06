package com.wlliu.blog.gateway.oauthgateway.mapper;

import com.wlliu.blog.gateway.oauthgateway.domain.SysPermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper {

    @Select("select * from sys_permission")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.wlliu.blog.gateway.oauthgateway.mapper.RoleMapper.findByPid"))
    })
    List<SysPermission> findAllPermissionWithRoles();


    @Select("select * from sys_permission")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleNames", column = "id", javaType = List.class,
                    many = @Many(select = "com.wlliu.blog.gateway.oauthgateway.mapper.RoleMapper.findRoleNamesByPid"))
    })
    List<SysPermission> findAllPermissionWithRoleNames();
}
