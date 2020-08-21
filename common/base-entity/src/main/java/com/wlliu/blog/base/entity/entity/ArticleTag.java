package com.wlliu.blog.base.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_article_tag")
public class ArticleTag implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String articleId;
    private String tagId;
    private String tagName;
    private Date createTime;
    private Date updateTime;
}
