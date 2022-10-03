package com.order;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.Dao.pojo.Goods;
import com.order.Service.GoodsService;
import com.order.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 测试分页
     */
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Test
    void testPage(){
        Page<Goods> goodsPage = goodsService.queryGoods(1, 2);
        List<Goods> records = goodsPage.getRecords();
        System.out.println(records);
    }


}
