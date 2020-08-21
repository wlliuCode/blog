package com.wlliu.blog.service.article.service;

import com.wlliu.blog.base.entity.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> selectTagByUserId(String id);
}
