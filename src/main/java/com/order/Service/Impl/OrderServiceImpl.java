package com.order.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.OrderService;
import com.order.mapper.GoodsMapper;
import com.order.mapper.OrderMapper;
import com.order.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Orders entity) {
        entity.setTime(new Date(System.currentTimeMillis()));
        entity.setArriveOrNot(false);
        entity.setId(null);
        int cnt = orderMapper.insert(entity);
        if (cnt > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addMore(Orders orders) {
//        String[] goodsId = goodsIdStr.split(",");
//        for (int i = 0; i < goodsId.length; i++) {
//            Orders orders = new Orders();
//            orders.setBuyId(buyId);
//            orders.setSellId(sellId);
//            orders.setGoodsId(Long.parseLong(goodsId[i]));
//            if (!save(orders)) {
//                return false;
//            }
//        }
//        return true;
        orders.setTime(new Date(System.currentTimeMillis()));
        orders.setArriveOrNot(false);
        orders.setId(null);
        return orderMapper.insert(orders) > 0;
    }

    @Override
    public boolean deleteMore(String idStr) {
        String[] ids = idStr.split(",");
        for (int i = 0; i < ids.length; i++) {
            if (!this.removeById(Long.parseLong(ids[i]))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteRecord(Integer buyId) {
        int i = orderMapper.deleteById(buyId);
        return i > 0;
    }

    @Override
    public boolean checkParams(Orders orders) {
        Integer buyId = Math.toIntExact(orders.getBuyId());
        String goodsIds = orders.getGoodsIds();
        String[] split = goodsIds.split(",");
        User user = userMapper.selectById(buyId);
        for(String i : split)
        {
            if (goodsMapper.selectById(i) == null) {
                return false;
            }
        }
        return user != null;
    }

    public Page<Orders> userQuery(Long id, int index, int size) {
        Page<Orders> ordersPage = new Page<>(index,size);
        QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>();
        wrapper.eq("buyId",id);
        ordersPage = this.page(ordersPage,wrapper);
        return ordersPage;
    }

    public Page<Orders> BusinessQuery(Long id,int index,int size) {
        Page<Orders> ordersPage = new Page<>(index,size);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("sellId",id);
        return this.page(ordersPage,wrapper);
    }

    @Override
    public Boolean arrive(Long id) {
        Orders order = orderMapper.selectById(id);
        if (order == null) {
            return false;
        }
        if (order.getArriveOrNot()) {
            return true;
        }
        order.setArriveOrNot(true);
        int cnt = orderMapper.updateById(order);
        return cnt > 0;
    }

    @Override
    public Boolean arriveMore(String idStr) {
        String[] ids = idStr.split(",");
        for (int i = 0; i < ids.length; i++) {
            //log.info(ids[i]);
            if (!arrive(Long.parseLong(ids[i])))
                return false;
        }
        return true;
    }

    @Override
    public Orders selectById(Long id) {
        Orders orders = null;
        orders = orderMapper.selectById(id);
        String goodsIds = orders.getGoodsIds();
        orders.setGoodsList(Arrays.asList(goodsIds.split(",")));
        return orders;
    }
}
