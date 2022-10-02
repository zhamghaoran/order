package com.order.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.BusinessService;
import com.order.Service.GoodsService;
import com.order.mapper.BusinessMapper;
import com.order.mapper.GoodsMapper;
import com.order.mapper.OrderMapper;
import com.order.mapper.UserMapper;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Response addGoods(String UID, Goods goods) {
        goods.setBelong(UID);
        int insert = goodsMapper.insert(goods);
        return new Response().easyReturn(insert);
    }



    @Override
    public Goods findGoodsById(Integer goodsId) {
        return businessMapper.selectById(goodsId);

    }

    @Override
    public void deleteGoods(Integer goodsId) {
        businessMapper.deleteGoodById(goodsId);
    }

    @Override
    public List<User> getAllBusiness() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getRole,1);
        return userMapper.selectList(queryWrapper);
    }
}
