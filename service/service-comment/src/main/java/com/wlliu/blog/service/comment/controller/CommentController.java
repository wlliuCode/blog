package com.wlliu.blog.service.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Comment;
import com.wlliu.blog.service.comment.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentDao commentDao;

    @GetMapping("getComment")
    public Page<Comment> getComment() {
        Page<Comment> commentPage = new Page<>(1, 10);
        return commentDao.selectPage(commentPage, null);
    }
}
