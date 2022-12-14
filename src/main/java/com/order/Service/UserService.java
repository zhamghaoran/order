package com.order.Service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.Dao.pojo.Address;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.util.Response;


import java.util.List;

public interface UserService {
    User FindUserByUID(String uid);

    User Login(String username, String password);

    Response Register(String UID);

    Orders AddOrder(User user, Goods goods,User business) ;

    void deleteOrder(Integer OrderId);

    List<Orders> QueryUserOrders(User user);

    void finishOrder(User user, Integer OrderId);

    List<Orders> QueryAllOrders();

    Page<User> getAllBusiness(int index, int size);

    Response insertUser(User user);

    Response selectAddress();

    Response addAddress(Address address);

    Response deleteAddress(String Id);
}
