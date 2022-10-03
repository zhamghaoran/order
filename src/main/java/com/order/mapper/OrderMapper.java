package com.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.order.Dao.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Orders> {
}
