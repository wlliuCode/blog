package com.wlliu.blog.service.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlliu.blog.base.entity.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleTagDao extends BaseMapper<ArticleTag> {
}
