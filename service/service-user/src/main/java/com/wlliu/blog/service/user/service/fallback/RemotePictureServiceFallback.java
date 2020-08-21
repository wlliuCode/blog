package com.wlliu.blog.service.user.service.fallback;

import com.wlliu.blog.base.entity.entity.Picture;
import com.wlliu.blog.base.result.result.Result;
import com.wlliu.blog.service.user.service.RemotePictureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemotePictureServiceFallback implements RemotePictureService {
    @Override
    public Result getPictureListById(List<String> list) {
        return null;
    }

    @Override
    public Result getPictureById(String id) {
        Picture picture = new Picture();
        picture.setPicId(id);
        picture.setPicUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3505980253,3135039336&fm=26&gp=0.jpg");
        return Result.error().message("获取图片失败").data("picture",picture);
    }
}
