package com.wlliu.blog.service.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlliu.blog.base.service.entity.CommentReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentReplyDao extends BaseMapper<CommentReply> {
    List<CommentReply> selectByCommentId(String commentId);
    List<CommentReply> selectByParentReplyId(String parentReplyId);
}
