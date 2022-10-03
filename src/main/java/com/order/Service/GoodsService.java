package com.order.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.order.Dao.pojo.Goods;
import com.order.util.Response;

public interface GoodsService extends IService<Goods> {
    boolean save(Goods goods);

    Goods queryById(Long id);

    boolean delete(String ids);

    Page<Goods> queryGoods(Long id,int index, int size);

    Response modify(Goods goods);
}
