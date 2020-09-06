package com.wlliu.blog.gateway.oauthauth.mapper;

import com.wlliu.blog.gateway.oauthauth.domain.SysPermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper {

    @Select("select * from sys_user where username")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.wlliu.blog.gateway.oauthauth.mapper.RoleMapper.findByPid"))
    })
    List<SysPermission> findAllPermissionWithRoles();
}
