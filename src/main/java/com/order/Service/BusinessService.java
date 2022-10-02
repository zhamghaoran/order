package com.order.Service;

import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.util.Response;

import java.util.List;

public interface BusinessService {

    Response addGoods(String name, Goods goods);


    Goods findGoodsById(Integer goodsId);

    void deleteGoods(Integer goodsId);





    List<User> getAllBusiness();
}
