package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_category")
public class Category implements Serializable {
    @TableId(value = "category_id", type = IdType.ASSIGN_ID)
    private String categoryId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
}
