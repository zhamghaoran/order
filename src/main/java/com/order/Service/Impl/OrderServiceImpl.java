package com.order.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.Dao.pojo.Orders;
import com.order.Service.OrderService;
import com.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public boolean save(Orders entity) {
        entity.setTime(new Date(System.currentTimeMillis()));
        entity.setArriveOrNot(false);
        entity.setId(null);
        int cnt = orderMapper.insert(entity);
        if(cnt>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addMore(Long sellId, String goodsIdStr, Long buyId) {
        String[]  goodsId = goodsIdStr.split(",");
        try {
            for (int i = 0; i < goodsId.length; i++) {
                Orders orders = new Orders();
                orders.setBuyId(buyId);
                orders.setSellId(sellId);
                orders.setGoodsId(Long.parseLong(goodsId[i]));
                if (!save(orders)) {
                    throw new Exception("出错辣");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteMore(String idStr) {
        String[] ids = idStr.split(",");
        try {
            for (int i = 0; i < ids.length; i++) {
                if (!this.removeById(Long.parseLong(ids[i]))) {
                    throw new Exception("出错辣");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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
    public Boolean arrive(Long id) throws Exception {
        Orders order = orderMapper.selectById(id);
        if (order.getArriveOrNot()){
            throw new Exception("这个订单已经完成");
        }
        order.setArriveOrNot(true);
        int cnt = orderMapper.updateById(order);
        if (cnt>0)return true;
        return false;
    }

    @Override
    public Boolean arriveMore(String idStr) {
        String[] ids = idStr.split(",");
        try {
            for (int i = 0; i < ids.length; i++) {
                log.info(ids[i]);
                arrive(Long.parseLong(ids[i]));
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Orders selectById(Long id) {
        Orders orders = null;
        orders = orderMapper.selectById(id);
        return orders;
    }
}
