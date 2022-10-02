package com.order.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Component;



@Component
public class TokenCheck {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

}
