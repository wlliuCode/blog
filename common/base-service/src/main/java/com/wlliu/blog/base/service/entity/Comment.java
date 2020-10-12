package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_comment")
public class Comment implements Serializable {
    @TableId(value = "comment_id", type = IdType.ASSIGN_ID)
    private String commentId;
    private String articleId;
    private String fromUserId;
    private String toUserId;
    private String commentContent;
    private String topicType;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private String fromUserName;
    @TableField(exist = false)
    private String fromUserAvatar;
    @TableField(exist = false)
    private String toUserName;
    @TableField(exist = false)
    private String toUserAvatar;
    @TableField(exist = false)
    private List<CommentReply> commentReplies;
}
