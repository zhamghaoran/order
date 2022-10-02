package com.order.mapper;

import com.order.Dao.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BusinessMapper {
    Goods findGoodsById(@Param("goodsId") Integer goodsId);

    void addGood(@Param("UID") String UID, @Param("goods") Goods goods);

    void deleteGoodById(@Param("goodsId") Integer goodsId);
}