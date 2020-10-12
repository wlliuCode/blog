package com.wlliu.blog.service.article.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Article;
import com.wlliu.blog.base.service.entity.Picture;
import com.wlliu.blog.base.service.entity.User;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.service.article.dao.ArticleDao;
import com.wlliu.blog.service.article.service.UserService;
import io.lettuce.core.dynamic.annotation.Param;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    private UserService userService;

    @PostMapping("list")
    public Result getArticleList(@Param("current") Integer current,
                             @Param("pageSize") Integer pageSize) {
        Page<Article> articlePage = new Page<>(current, pageSize);
        return Result.ok().data("articleList", articleDao.selectPage(articlePage, null));
    }

    @PostMapping("add")
    public Result createArtilce(@RequestBody Article article) {
        articleDao.insert(article);
        return Result.ok();
    }

    @PostMapping("{id}")
    public Result getArticleById(@PathVariable("id") String id) {
        Article article = articleDao.selectById(id);
        User user = userService.getUserById(article.getAuthorId());
        System.out.println(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("article",article);
        map.put("authorInfo",user);
        System.out.println(map);
        return Result.ok().data(map);
    }


}
