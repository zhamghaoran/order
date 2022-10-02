package com.order.Service;


import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface UserService {
    User FindUserByUID(String uid);

    User Login(String username, String password);

    String Register(String username, String password);

    Orders AddOrder(User user, Goods goods,User business) ;

    void deleteOrder(Integer OrderId);

    List<Orders> QueryUserOrders(User user);

    void finishOrder(User user, Integer OrderId);

    List<Orders> QueryAllOrders();
}
