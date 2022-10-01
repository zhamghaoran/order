package com.order.Service.Impl;

import com.order.Dao.pojo.User;
import com.order.Service.UserService;
import org.springframework.stereotype.Service;

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
}
