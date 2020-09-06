package com.wlliu.blog.service.user.service.fallback;

import com.wlliu.blog.base.service.entity.Picture;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.service.user.service.RemotePictureService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemotePictureServiceFallbackFactory implements FallbackFactory<RemotePictureService> {
    @Override
    public RemotePictureService create(Throwable throwable) {
        /*return new RemotePictureService() {
            @Override
            public Picture getPictureById(String id) {
                System.out.println(JSON.toJSONString(throwable));
                Picture picture = new Picture();
                picture.setPicId(id);
                picture.setPicUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3505980253,3135039336&fm=26&gp=0.jpg");
                return picture;
            }
        };*/

        return new RemotePictureService() {
            @Override
            public Result getPictureListById(List<String> list) {
                return null;
            }

            @Override
            public Result getPictureById(String id) {
                Picture picture = new Picture();
                picture.setPicUrl("http://47.100.202.70:8888/group1/M00/00/00/rBMmn18dJm6AOdTZAARx_an6HHo416.jpg");
                return Result.error().message("获取图片失败").data("picture", picture);
            }
        };


    }
}
