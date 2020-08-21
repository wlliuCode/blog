package com.wlliu.blog.service.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlliu.blog.base.entity.entity.Picture;
import com.wlliu.blog.base.entity.entity.User;
import com.wlliu.blog.base.result.result.Result;
import com.wlliu.blog.service.user.dao.UserDao;
import com.wlliu.blog.service.user.service.RemotePictureService;
import com.wlliu.blog.service.user.service.UserService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /*@Autowired
    RestTemplate restTemplate;*/

    /*@Autowired
    DiscoveryClient discoveryClient;*/

    /*远程调用api*/
    @Autowired
    RemotePictureService remotePictureService;


    @Autowired
    RocketMQTemplate rocketMqTemplate;

    @Override
    public PageInfo<User> getUserList(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.selectList(null);

        /*List<ServiceInstance> instances = discoveryClient.getInstances("picture-service");
        ServiceInstance serviceInstance = instances.get(0);*/

        for (User user : users) {
            String avatarId = user.getAvatarId();

            /*Picture picture = restTemplate.getForObject("http://" + serviceInstance.getHost()
                    + ":" + serviceInstance.getPort() + "/picture/" + avatarId, Picture.class);*/

            /*ribbon 实现负载均衡*/
            /*Picture picture = restTemplate.getForObject("http://service-picture/picture/"
                    + avatarId, Picture.class);*/

            /*fegin 实现接口调用及负载均衡*/

            Result result = remotePictureService.getPictureById(avatarId);
            Map<String, Object> data = result.getData();

            String pictureStr = JSONObject.toJSONString(data.get("picture"));
            Picture picture = JSON.parseObject(pictureStr, Picture.class);

            String picUrl = picture.getPicUrl();
            user.setAvatarUrl(picUrl);
        }

        rocketMqTemplate.convertAndSend("user-topic",users);

        return new PageInfo<>(users);
    }

    @Override
    @SentinelResource(value = "getMessage",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = Fallback.class,
            fallback = "fallback"
    )
    public String getMessage() {
        return "测试SentinelResource";
    }



}
