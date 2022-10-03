package com.order.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.order.Dao.pojo.Orders;


public interface OrderService extends IService<Orders> {

    Page<Orders> userQuery(Long id, int index, int size);

    Page<Orders> BusinessQuery(Long id,int index,int size);

    Boolean arrive(Long id) throws Exception;

    Orders selectById(Long id);

    Boolean arriveMore(String ids);

    boolean addMore(Long sellId, String goodsIds, Long buyId);

    boolean deleteMore(String ids);
}
