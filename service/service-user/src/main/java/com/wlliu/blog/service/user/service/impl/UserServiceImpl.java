package com.wlliu.blog.service.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlliu.blog.base.service.entity.Picture;
import com.wlliu.blog.base.service.entity.User;
import com.wlliu.blog.base.service.exception.GlobalException;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.base.service.result.ResultCodeEnum;
import com.wlliu.blog.base.utils.utils.FormUtils;
import com.wlliu.blog.base.utils.utils.JwtInfo;
import com.wlliu.blog.base.utils.utils.JwtUtils;
import com.wlliu.blog.base.utils.utils.MD5;
import com.wlliu.blog.service.user.dao.UserDao;
import com.wlliu.blog.service.user.entity.vo.LoginVo;
import com.wlliu.blog.service.user.entity.vo.RegisterVo;
import com.wlliu.blog.service.user.service.RemotePictureService;
import com.wlliu.blog.service.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    StringRedisTemplate redisTemplate;
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
    public List<User> getUserList() {

        List<User> users = userDao.selectList(null);
        System.out.println(users);

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
            System.out.println(result);

            String pictureStr = JSONObject.toJSONString(data.get("picture"));
            Picture picture = JSON.parseObject(pictureStr, Picture.class);

            String picUrl = picture.getPicUrl();
            user.setAvatarUrl(picUrl);
        }

        rocketMqTemplate.convertAndSend("user-topic", users);

        return users;
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

    @Override
    public void register(RegisterVo registerVo) {
        System.out.println(registerVo);

        /*校验参数*/
        String username = registerVo.getUsername();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        System.out.println("输入注册码：" + code);


        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)) {
            throw new GlobalException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code)) {
            throw new GlobalException(ResultCodeEnum.PARAM_ERROR);
        }

        String checkCode = (String) redisTemplate.opsForValue().get(mobile);
        System.out.println("缓存注册码：" + checkCode);

        if (!code.equals(checkCode)) {
            throw new GlobalException(ResultCodeEnum.CODE_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = userDao.selectCount(queryWrapper);
        if (count > 0) {
            throw new GlobalException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }
        //注册
        User user = new User();
        user.setUsername(username);
        user.setMobile(mobile);
        user.setPassword(MD5.encrypt(password));
        user.setAvatarId("1295022525402697729");
        user.setIsDisabled("0");
        userDao.insert(user);
    }

    @Override
    public User getUserById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public String login(LoginVo loginVo) {


        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();


        //参数合法
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)) {
            throw new GlobalException(ResultCodeEnum.PARAM_ERROR);
        }

        //手机号
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        User user = userDao.selectOne(queryWrapper);
        if (user == null) {
            throw new GlobalException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        //密码
        if (!MD5.encrypt(password).equals(user.getPassword())) {
            throw new GlobalException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //禁用
        if ("1".equals(user.getIsDisabled())){
            throw new GlobalException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //登录
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(user.getUserId());
        jwtInfo.setUsername(user.getUsername());
        jwtInfo.setAvatar(user.getAvatarId());

        return JwtUtils.getJwtToken(jwtInfo, 1800);
    }


}
