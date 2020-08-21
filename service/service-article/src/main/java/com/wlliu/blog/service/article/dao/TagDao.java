package com.wlliu.blog.service.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlliu.blog.base.entity.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao extends BaseMapper<Tag> {

    List<Tag> selectTagByUserId(String id);
}
