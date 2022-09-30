package com.order.Service;


import com.order.Dao.pojo.User;

public interface UserService {
    User FindUserByUID(String uid);
}
