package com.wlliu.blog.gateway.oauthauth.dao;

import com.wlliu.blog.gateway.oauthauth.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from sys_user where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                many = @Many(select = "com.wlliu.blog.gateway.oauthauth.dao.RoleDao.findByUid"))
    })
    SysUser findByName(String username);

}
