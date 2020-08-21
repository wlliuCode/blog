package com.wlliu.blog.service.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.entity.entity.ArticleTag;
import com.wlliu.blog.base.entity.entity.Tag;
import com.wlliu.blog.service.article.dao.ArticleTagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("articleTag")
public class ArticleTagController {

    @Autowired
    ArticleTagDao articleTagDao;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("")
    public Page<ArticleTag> getArticleTag(){
        Page<ArticleTag> articleTagPage = new Page<>(1, 10);
        return articleTagDao.selectPage(articleTagPage, null);
    }

    @GetMapping("save/tag/{tagId}")
    public ArticleTag saveArticleTag(@PathVariable("tagId") String tagId){
        List<ServiceInstance> instances = discoveryClient.getInstances("article-service");
        ServiceInstance serviceInstance = instances.get(0);

        Tag tag = restTemplate.getForObject("http://"+serviceInstance.getHost()
                +":"+serviceInstance.getPort()+"/tag/" + tagId, Tag.class);

        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId("111");
        articleTag.setTagId(tagId);
        if (tag != null) {
            articleTag.setTagName(tag.getTagName());
        }
        articleTag.setCreateTime(new Date());
        articleTag.setUpdateTime(new Date());
        articleTagDao.insert(articleTag);
        return articleTag;
    }

}
