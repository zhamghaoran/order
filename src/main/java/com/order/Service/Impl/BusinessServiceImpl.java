package com.order.Service.Impl;

import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.User;
import com.order.Service.BusinessService;
import com.order.mapper.BusinessMapper;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public User FindBusinessByUID(String uid) {
        return null;
    }

    @Override
    public Response addGoods(String UID, Goods goods) {

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

    @Override
    public Goods findGoodsById(Integer goodsId) {
        return businessMapper.findGoodsById(goodsId);

    }

    @Override
    public void deleteGoods(Integer goodsId) {
        businessMapper.deleteGoodById(goodsId);
    }
}
