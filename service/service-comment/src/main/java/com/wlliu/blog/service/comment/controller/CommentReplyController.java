package com.wlliu.blog.service.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.entity.entity.CommentReply;
import com.wlliu.blog.service.comment.dao.CommentReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentReplyController {

    @Autowired
    CommentReplyDao commentReplyDao;


    @GetMapping("getCommentReply")
    public Page<CommentReply> getCommentReply() {
        Page<CommentReply> commentReplyPage = new Page<>(1, 10);
        return commentReplyDao.selectPage(commentReplyPage, null);
    }
}
