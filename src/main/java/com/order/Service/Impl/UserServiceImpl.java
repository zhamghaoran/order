package com.order.Service.Impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.UserService;
import com.order.mapper.UserMapper;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User FindUserByUID(String uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public User Login(String username, String password) {
        return null;
    }

    @Override
    public Response Register(String UID) {
        User user = new User(UID,1);
        Map<String,String> map = new HashMap<>();
        if (userMapper.insert(user) != 0) {
            map.put("role","1");
            return new Response().easyReturn(map);
        } else {
            map.put("role","-1");
            return new Response().badReturn(map);
        }
    }

    @Override
    public Orders AddOrder(User user, Goods goods, User business) {
        return null;
    }

    @Override
    public void deleteOrder(Integer OrderId) {

    }

    @Override
    public List<Orders> QueryUserOrders(User user) {
        return null;
    }

    @Override
    public void finishOrder(User user, Integer OrderId) {

    }

    @Override
    public List<Orders> QueryAllOrders() {
        return null;
    }
}
