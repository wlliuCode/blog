package com.wlliu.blog.service.picture.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wlliu.blog.base.service.entity.Picture;
import com.wlliu.blog.service.picture.dao.PictureDao;
import com.wlliu.blog.service.picture.service.PictureService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/jpeg", "image/jpg", "image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringApplication.class);


    @Autowired
    PictureDao pictureDao;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

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
    public String uploadImage(MultipartFile file) throws IOException {
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //校验文件类型
        /*String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            LOGGER.info("文件类型不合法:" + originalFilename);
            return null;
        }*/
        //校验文件内容
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if (bufferedImage == null) {
            LOGGER.info("文件内容不合法:" + originalFilename);
            return null;
        }
        //保存到服务器
        //获取文件后缀
        String s = StringUtils.substringAfterLast(originalFilename, ".");
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), s, null);
        //返回url 进行回显
        Picture picture = new Picture();
        picture.setPicUrl("http://47.100.202.70:8888/" + storePath.getFullPath());
        picture.setCreateTime(new Date());
        picture.setUpdateTime(new Date());
        pictureDao.insert(picture);

        System.out.println("http://47.100.202.70:8888/" + storePath.getFullPath());

        return "http://47.100.202.70:8888/" + storePath.getFullPath();
    }
}
