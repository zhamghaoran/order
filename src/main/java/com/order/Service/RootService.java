package com.order.Service;

import com.order.Dao.pojo.User;

public interface RootService {
    User FindRootByUID(String uid);

    User Login(String username, String password);

}
