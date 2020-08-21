package com.wlliu.blog.service.picture.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlliu.blog.base.entity.entity.Picture;
import com.wlliu.blog.base.result.result.Result;
import com.wlliu.blog.service.picture.dao.PictureDao;
import com.wlliu.blog.service.picture.service.PictureService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("picture")
public class PictureController {

    @Autowired
    PictureDao pictureDao;

    @Autowired
    PictureService pictureService;

    @GetMapping("")
    public Result getPicture(@Param("pageNum") int pageNum,
                             @Param("pageSize") int pageSize) {
        if (pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == 0) {
            pageSize = 10;
        }
        Page<Picture> pictureList = pictureService.getPictureList(pageNum, pageSize);
        return Result.ok().data("pictureList",pictureList);
    }

    @PostMapping("/ids")
    public Result getPictureListById(List<String> list){
        return Result.ok().data("pictureList",pictureService.getPictureListById(list));
    }

    @GetMapping("{id}")
    public Result getPictureById(@PathVariable("id") String id) {
        Picture picture = pictureService.getPictureById(id);
        return Result.ok().data("picture",picture);
    }

    @PostMapping("upload")
    public Result uploadImage(@Param("file") MultipartFile file) throws IOException {
        String url = pictureService.uploadImage(file);
        if (StringUtils.isEmpty(url)) {
            return Result.error().message("上传失败");
        }
        return Result.ok().data("picUrl",url);
    }
}
