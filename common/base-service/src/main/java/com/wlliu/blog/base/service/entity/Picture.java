package com.wlliu.blog.base.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_picture")
public class Picture implements Serializable {
    @TableId(value = "pic_id", type = IdType.ASSIGN_ID)
    private String picId;
    private String oldName;
    private String picName;
    private String picUrl;
    private String summary;
    private String categoryId;
    private Date createTime;
    private Date updateTime;
}
