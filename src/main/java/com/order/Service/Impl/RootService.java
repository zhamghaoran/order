package com.order.Service.Impl;

import com.order.Dao.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class RootService implements com.order.Service.RootService {
    @Override
    public User FindRootByUID(String uid) {
        return null;
    }

    @Override
    public User Login(String username, String password) {
        return null;
    }
}
