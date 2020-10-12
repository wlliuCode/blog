package com.wlliu.blog.service.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.wlliu.blog.base.service.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao extends BaseMapper<Comment> {
    List<Comment> selectByArticleId(String articleId);
}
