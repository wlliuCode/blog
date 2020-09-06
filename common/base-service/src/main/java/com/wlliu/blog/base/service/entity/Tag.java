package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_tag")
public class Tag implements Serializable {
    @TableId(value = "tag_id",type = IdType.ASSIGN_ID)
    private String tagId;
    private String tagName;
    private Date createTime;
    private Date updateTime;
}
