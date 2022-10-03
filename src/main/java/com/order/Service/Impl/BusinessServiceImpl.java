package com.order.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

import java.lang.annotation.ElementType;
import java.util.List;

@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Goods> implements BusinessService {
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
        goods.setId(0);
        int insert = goodsMapper.insert(goods);
        if (insert >= 0)
            return new Response().easyReturn(insert);
        else
            return new Response().badReturn("添加商品失败");
    }


    @Override
    public Goods findGoodsById(Integer goodsId) {
        return businessMapper.selectById(goodsId);

    }

    @Override
    public Response deleteGoods(Integer goodsId) {
        int i = businessMapper.deleteById(goodsId);
        if (i >= 0)
            return new Response().easyReturn("删除成功");
        else
            return new Response().badReturn("删除失败");
    }


}
