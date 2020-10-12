package com.wlliu.blog.service.picture.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.service.entity.Picture;
import com.wlliu.blog.service.picture.dao.PictureDao;
import com.wlliu.blog.service.picture.service.PictureService;
import com.wlliu.blog.service.picture.utils.MinioUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/jpeg", "image/jpg", "image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringApplication.class);

    @Autowired
    PictureDao pictureDao;

    @Autowired
    private MinioUtil minioUtil;

    @Value("${minio.endpoint}")
    String endpoint;

    @Override
    public Page<Picture> getPictureList(Integer pageNum, Integer pageSize) {
        Page<Picture> picturePage = new Page<>(pageNum, pageSize);
        return pictureDao.selectPage(picturePage, null);
    }

    @Override
    public List<Picture> getPictureListById(List<String> list) {
        return pictureDao.selectBatchIds(list);
    }

    @Override
    public Picture getPictureById(String id) {
        return pictureDao.selectById(id);
    }

    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        boolean isUpload = minioUtil.putObject("img", file.getOriginalFilename(),
                file);
        return endpoint+"img/"+file.getOriginalFilename();
    }

}
