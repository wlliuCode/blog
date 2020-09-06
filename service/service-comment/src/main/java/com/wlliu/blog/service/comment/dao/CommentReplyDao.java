package com.wlliu.blog.service.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlliu.blog.base.service.entity.CommentReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentReplyDao extends BaseMapper<CommentReply> {
}
