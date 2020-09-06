package com.wlliu.blog.service.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Tag;
import com.wlliu.blog.service.article.dao.TagDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("tag")
public class TagController {


    @Autowired
    private TagDao tagDao;

    @GetMapping("selectTagByUserId")
    public List<Tag> selectTagByUserId(@Param("id") String id) {
        return tagDao.selectTagByUserId(id);
    }

    @GetMapping("{tagId}")
    public Tag getTagByUserId(@PathVariable("tagId") String tagId) {
        return tagDao.selectById(tagId);
    }

    @GetMapping("save")
    public String saveTag() {
        Tag tag = new Tag();
        tag.setTagName("asdasd");
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        System.out.println(tag.getTagId());
        tagDao.insert(tag);
        return tag.getTagId();
    }
    @GetMapping("")
    public Page<Tag> getTag() {
        Page<Tag> tagPage = new Page<>(1, 10);
        return tagDao.selectPage(tagPage, null);
    }
}
