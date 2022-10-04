package com.order.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.order.Dao.pojo.Orders;


import java.util.List;

public interface OrderService extends IService<Orders> {

    Page<Orders> userQuery(Long id, int index, int size);

    Page<Orders> BusinessQuery(Long id,int index,int size);

    Boolean arrive(Long id);

    Orders selectById(Long id);

    Boolean arriveMore(String ids);

    boolean addMore(Orders entity);

    boolean deleteMore(String ids);

    boolean deleteRecord(Integer buyId);

    boolean checkParams(Orders orders);
}
