package com.wlliu.blog.service.picture.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlliu.blog.base.entity.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PictureDao extends BaseMapper<Picture> {
}
