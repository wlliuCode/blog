package com.wlliu.blog.service.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Comment;
import com.wlliu.blog.base.service.entity.CommentReply;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.service.comment.dao.CommentReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("commentReply")
public class CommentReplyController {

    @Autowired
    CommentReplyDao commentReplyDao;

    @PostMapping("add")
    public Result addComment(@RequestBody CommentReply commentReply) {
        System.out.println(commentReply);
        commentReplyDao.insert(commentReply);
        return Result.ok();
    }

    @GetMapping("{commentId}")
    public Result getCommentReplyByCommentId(@PathVariable("commentId") String commentId) {
        return Result.ok().data("commentReply",
                commentReplyDao.selectByCommentId(commentId));
    }
}
