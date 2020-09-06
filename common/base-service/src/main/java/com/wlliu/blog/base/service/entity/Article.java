package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_article")
public class Article implements Serializable {

    @TableId(value = "article_id", type = IdType.ASSIGN_ID)
    private String articleId;
    private String title;
    private String summary;
    private String content;
    private String imageId;
    private String authorId;
    private Date createTime;
    private Date updateTime;
}
