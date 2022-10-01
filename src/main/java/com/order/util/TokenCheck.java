package com.order.util;

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

    public String addToken(String username,String password,Integer enumCode) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String token = SecureUtils.getMD5(username + password + new Date());
        valueOperations.set(token,username);
        valueOperations.set(username,enumCode);
        // 设置redis过期时间
        redisTemplate.expire(token, Duration.ofSeconds(20 * 60 * 60));
        redisTemplate.expire(username,Duration.ofSeconds(20 * 60 * 60));
        return token;
    }
}
