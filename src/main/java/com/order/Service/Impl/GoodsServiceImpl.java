package com.order.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.Dao.pojo.Goods;
import com.order.Service.GoodsService;
import com.order.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 修改商品，根据id修改商品参数
     * @param goods
     * @return
     */
    @Override
    public boolean modify(Goods goods) {
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",goods.getId());
        int cnt = goodsMapper.update(goods, updateWrapper);
        if (cnt>0)return true;
        return false;
    }

    /**
     * 删除商品
     * @param idStr
     * @return
     */
    @Override
    public boolean delete(String idStr) {
        String[] ids = idStr.split(",");
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.in("id", (Object) ids);
        int cnt = goodsMapper.delete(wrapper);
        return cnt > 0;
    }

    /**
     * 新增商品
     * @param goods
     * @return
     */
    @Override
    public boolean save(Goods goods){
        int cnt = goodsMapper.insert(goods);
        if(cnt>0)return true;
        return false;
    }

    /**
     * 查询所有的商品
     * @return
     */
    @Override
    public Page<Goods> queryGoods(Long id,int index, int size) {
        Page<Goods> goodsPage = new Page<>(index,size);
        goodsPage = this.page(goodsPage,new QueryWrapper<Goods>().eq("belong", id));
        return goodsPage;
    }

    /**
     * 根据id查询单个商品
     * @param id
     * @return
     */
    @Override
    public Goods queryById(Long id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return goodsMapper.selectOne(wrapper);
    }

}
