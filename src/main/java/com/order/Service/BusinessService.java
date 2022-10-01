package com.order.Service;

import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.User;
import com.order.util.Response;

public interface BusinessService {
    User FindBusinessByUID(String uid);

    Response addGoods(String name, Goods goods);

    User Login(String username, String password);

    String Register(String username, String password);

    Goods findGoodsById(Integer goodsId);

    void deleteGoods(Integer goodsId);
}
