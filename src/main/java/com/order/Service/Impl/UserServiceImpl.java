package com.order.Service.Impl;

import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User FindUserByUID(String uid) {
        return null;
    }

    @Override
    public User Login(String username, String password) {
        return null;
    }

    @Override
    public String Register(String username, String password) {
        return null;
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
