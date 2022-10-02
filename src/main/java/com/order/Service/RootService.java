package com.order.Service;

import com.order.Dao.pojo.User;

import java.util.List;

public interface RootService {
    User FindRootByUID(String uid);

    User Login(String username, String password);

    void addBusiness(User user);

    boolean DeleteBusiness(Integer BusinessId) ;

    List<User> QueryAllBusiness();



}
