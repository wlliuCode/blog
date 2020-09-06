package com.wlliu.blog.service.article.service;


import com.wlliu.blog.base.service.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> selectTagByUserId(String id);
}
