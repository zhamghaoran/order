package com.order.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.Dao.pojo.Address;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.UserService;
import com.order.mapper.AddressMapper;
import com.order.mapper.UserMapper;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public User FindUserByUID(String uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public User Login(String username, String password) {
        return null;
    }

    @Override
    public Response Register(String UID) {
        User user = new User(UID,0);
        Map<String,String> map = new HashMap<>();
        if (userMapper.insert(user) != 0) {
            map.put("role","0");
            return new Response().easyReturn(map);
        } else {
            map.put("role","-1");
            return new Response().badReturn(map);
        }
    }
    @Override
    public Orders AddOrder(User user, Goods goods, User business) {
        return null;
    }

    @Override
    public void deleteOrder(Integer OrderId) {

    }

    @Override
    public List<Orders> QueryUserOrders(User user) {
        return null;
    }

    @Override
    public void finishOrder(User user, Integer OrderId) {

    }

    @Override
    public List<Orders> QueryAllOrders() {
        return null;
    }

    @Override
    public Page<User> getAllBusiness(int index, int size) {
//        Page<Goods> goodsPage = new Page<>(index,size);
//        goodsPage = this.page(goodsPage,new QueryWrapper<Goods>().eq("belong", id));
        Page<User> UserPage = new Page<>(index,size);
        return this.page(UserPage,new QueryWrapper<User>().eq("role",1));
    }

    @Override
    public Response insertUser(User user) {
        int insert = userMapper.insert(user);
        if (insert > 0)
            return new Response().easyReturn("success");
        else return new Response().badReturn("failed");
    }

    @Override
    public Response selectAddress() {
        List<Address> addresses = addressMapper.selectAll();
        if (addresses == null) {
            return new Response().badReturn("failed");
        } else
            return new Response().easyReturn(addresses);
    }

    @Override
    public Response addAddress(Address address) {
        int insert = addressMapper.insert(address);
        if (insert > 0)
            return new Response().easyReturn("success");
        else return new Response().badReturn("failed");
    }

    @Override
    public Response deleteAddress(String Id) {
        int delete = addressMapper.deleteById(Id);
        if (delete > 0)
            return new Response().easyReturn("success");
        else
            return new Response().badReturn("failed");
    }
}
