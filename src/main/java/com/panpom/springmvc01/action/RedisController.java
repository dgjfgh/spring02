package com.panpom.springmvc01.action;


import com.panpom.springmvc01.bean.User;
import com.panpom.springmvc01.util.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by majianghua on 2018/5/18 0018.
 */
@Controller
public class RedisController extends BaseController{
    @Autowired
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("user")
    private String jedis(User user){
        System.out.println(user.getUname());
        String mjh = (String) RedisUtils.getValue(redisTemplate, "mjh");
        System.out.println(mjh);
        if (mjh==null) {
            RedisUtils.setStringValue(redisTemplate, "mjh", "hello");
            return "set";
        }
        return "get";
    }

}
