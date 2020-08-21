package com.wlliu.blog.base.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private Date createTime;
    private Date updateTime;
}
