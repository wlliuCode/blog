package com.wlliu.blog.service.picture.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.entity.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    Page<Picture> getPictureList(Integer pageNum, Integer pageSize);
    List<Picture> getPictureListById(List<String> list);

    Picture getPictureById(String id);

    String uploadImage(MultipartFile file) throws IOException;
}
