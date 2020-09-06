package com.wlliu.blog.service.article.service.impl;

import com.wlliu.blog.base.service.entity.Tag;
import com.wlliu.blog.service.article.dao.TagDao;
import com.wlliu.blog.service.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> selectTagByUserId(String id) {
        return tagDao.selectTagByUserId(id);
    }
}
