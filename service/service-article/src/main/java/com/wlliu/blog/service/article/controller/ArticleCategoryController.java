package com.wlliu.blog.service.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.entity.entity.ArticleCategory;
import com.wlliu.blog.service.article.dao.ArticleCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleCategoryController {

    @Autowired
    ArticleCategoryDao articleCategoryDao;

    @GetMapping("getArticleCategory")
    public Page<ArticleCategory> getArticleCategory(){
        Page<ArticleCategory> articleCategoryPage = new Page<>(1, 10);
        return articleCategoryDao.selectPage(articleCategoryPage, null);
    }
}
