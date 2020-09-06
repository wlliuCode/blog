package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_article_category")
public class ArticleCategory implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String articleId;
    private String categoryId;
    private Date createTime;
    private Date updateTime;
}
