package com.order.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.order.Dao.pojo.Orders;


import java.util.List;

public interface OrderService extends IService<Orders> {

    List<Orders> userQuery(Long id);

    List<Orders> BusinessQuery(Long id);

    Boolean arrive(Long id) throws Exception;

    Orders selectById(Long id);

    Boolean arriveMore(String ids);

    boolean addMore(Long sellId, String goodsIds, Long buyId);

    boolean deleteMore(String ids);
}
