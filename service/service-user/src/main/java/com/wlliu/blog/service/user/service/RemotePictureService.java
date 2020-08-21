package com.wlliu.blog.service.user.service;

import com.wlliu.blog.base.result.result.Result;
import com.wlliu.blog.service.user.service.fallback.RemotePictureServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author wlliu
 */
//指定微服务名称
@Service
@FeignClient(value = "service-picture",
        fallbackFactory = RemotePictureServiceFallbackFactory.class
        //fallback = RemotePictureServiceFallback.class
)
public interface RemotePictureService {

    @PostMapping("picture/ids")
    Result getPictureListById(List<String> list);

    @GetMapping("picture/{id}")
    Result getPictureById(@PathVariable("id") String id);

}
