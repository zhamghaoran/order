package com.order.Service;

import com.order.Dao.pojo.Business;
import com.order.util.Response;

public interface BusinessService {
    Business FindBusinessByUID(String uid);

    Response addGoods(String name, Integer price, String description, String picture, Integer businessID);

    Business Login(String username, String password);

    String Register(String username, String password);
}
