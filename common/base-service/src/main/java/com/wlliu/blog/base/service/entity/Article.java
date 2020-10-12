package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_article")
@Document(indexName = "article")
public class Article implements Serializable {

    @TableId(value = "article_id", type = IdType.ASSIGN_ID)
    @Id
    private String articleId;
    private String title;
    private String summary;
    private String content;
    private String imageId;
    private String authorId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
