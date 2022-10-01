package com.order.util;

import com.order.Dao.pojo.User;
import com.order.util.common.util.SecureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class TokenCheck {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

}
