package com.order.Service.Impl;

import com.order.Dao.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void addBusiness(User user) {

    }

    @Override
    public boolean DeleteBusiness(Integer businessId) {
        return false;
    }

    @Override
    public List<User> QueryAllBusiness() {
        return null;
    }


}
