package com.wlliu.blog.base.entity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_comment_reply")
public class CommentReply implements Serializable {
    @TableId(value = "reply_id")
    private String replyId;
    private String commentId;
    private String parentReplyId;
    private String replyType;
    private String replyContent;
    private String fromUserId;
    private String toUserId;
    private Date createTime;
    private Date updateTime;
}
