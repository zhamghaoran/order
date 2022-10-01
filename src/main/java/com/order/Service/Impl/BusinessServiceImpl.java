package com.order.Service.Impl;

import com.order.Dao.pojo.Business;
import com.order.Service.BusinessService;
import com.order.util.Response;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Override
    public Business FindBusinessByUID(String uid) {
        return null;
    }

    @Override
    public Response addGoods(String name, Integer price, String description, String picture, Integer businessID) {
        return null;
    }

    @Override
    public Business Login(String username, String password) {
        return null;
    }

    @Override
    public String Register(String username, String password) {
        return null;
    }
}
