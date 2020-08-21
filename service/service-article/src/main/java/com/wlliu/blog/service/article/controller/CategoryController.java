package com.wlliu.blog.service.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.entity.entity.Category;
import com.wlliu.blog.service.article.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @GetMapping("getCategory")
    public Page<Category> getCategory() {
        Page<Category> categoryPage = new Page<>(1, 10);
        return categoryDao.selectPage(categoryPage, null);
    }
}
