package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
