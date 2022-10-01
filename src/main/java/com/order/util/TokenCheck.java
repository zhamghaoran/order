package com.order.util;

import com.order.Dao.pojo.UserMessage;
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

    public String addToken(UserMessage user, Integer enumCode) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String token = SecureUtils.getMD5(user.getUsername() + user.getPassword() + new Date());
        valueOperations.set(token,user.getUsername());
        valueOperations.set(user.getUsername(),enumCode);

        // 设置redis过期时间
        redisTemplate.expire(token, Duration.ofSeconds(20 * 60 * 60));
        redisTemplate.expire(user.getUsername(),Duration.ofSeconds(20 * 60 * 60));
        return token;
    }
    public String checkToken(String token) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(token);
        if (o != null) {
            return "success";
        }
        else
            return "failed";
    }
}
