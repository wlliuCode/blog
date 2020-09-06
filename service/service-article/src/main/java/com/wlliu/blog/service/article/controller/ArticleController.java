package com.wlliu.blog.service.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Article;
import com.wlliu.blog.service.article.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    ArticleDao articleDao;

    @GetMapping("getArticle")
    public Page<Article> getArticle() {
        Page<Article> articlePage = new Page<>(1, 10);
        return articleDao.selectPage(articlePage, null);
    }
}
