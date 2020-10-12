package com.wlliu.blog.service.comment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Comment;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.service.comment.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentDao commentDao;

    @PostMapping("add")
    public Result addComment(@RequestBody Comment comment) {
        System.out.println(comment);
        commentDao.insert(comment);
        return Result.ok();
    }

    @PostMapping("{articleId}")
    public Result getComment(@PathVariable("articleId") String articleId) {
        return Result.ok().data("comment",commentDao.selectByArticleId( articleId));
    }

}
