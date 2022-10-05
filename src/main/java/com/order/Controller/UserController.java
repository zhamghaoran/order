package com.order.Controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.*;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RootService rootService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private OrderService orderService;
    @Autowired
    GoodsService goodsService;

    /**
     * 待定，需要修改
     *
     * @param UID
     * @return
     */
    @RequestMapping("/login")
    public Response GetUserRole(String UID) {
        User user = userService.FindUserByUID(UID);
        if (user == null) {
            return userService.Register(UID);
        }
        Map<String, String> map = new HashMap<>();
        if (user.getRole() == 1) {
            map.put("role", "1");
            return new Response().easyReturn(map);
        } else if (user.getRole() == 2) {
            map.put("role", "2");
            return new Response().easyReturn(map);
        } else if (user.getRole() == 0) {
            map.put("role", "0");
            return new Response().easyReturn(map);
        }
        return null;
    }

    /**
     * 添加商品
     *
     * @param UID
     * @param goods
     * @return
     */
    @RequestMapping(value = "/add/good", method = RequestMethod.POST)
    public Response addGoods(String UID, Goods goods) {
        User user = userService.FindUserByUID(UID);
        if (user == null) {
            return new Response().badReturn("非法用户");
        }
        if (user.getRole() != 1)
            return new Response().badReturn("权限不足");
        return businessService.addGoods(UID, goods);
    }

    /**
     * 删除商品
     *
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/delete/good",method = RequestMethod.POST)
    public Response deleteGoods(Integer goodsId) {
        if (businessService.findGoodsById(goodsId) == null) {
            return new Response().badReturn("商品ID错误");
        }
        return businessService.deleteGoods(goodsId);
    }


    //添加商品
//    @RequestMapping("/add/goods")
//    public Response addsGoods(Goods, MultipartFile file){
//        if(!file.isEmpty()) {
//            try {
//                Base64.Encoder encoder = Base64.getEncoder();
//                String ImgStr = encoder.encodeToString(file.getBytes());
//                goods.setPicture(ImgStr);
//                goodsService.save(goods);
//                return new Response().easyReturn("success");
//            } catch (IOException e) {
//                return new Response().badReturn("failed");
//            }
//        }else {
//            return new Response().badReturn("failed");
//        }
//    }
    //批量删除商品
    @RequestMapping(value = "/delete/goods",method = RequestMethod.POST)
    public Response deleteGoods(String ids) {
        if (goodsService.delete(ids))
            return new Response().easyReturn("success");
        return new Response().badReturn("failed");
    }

    //查询所有商品信息
    @RequestMapping("query/goods")
    public Response queryGoods(Long id, int index, int size) {
        Page<Goods> list = null;
        list = goodsService.queryGoods(id, index, size);
        if (list == null) return new Response().badReturn("failed");
        return new Response().easyReturn(list.getRecords());
    }

    //修改商品信息
    @RequestMapping("/modify/goods")
    public Response modifyGoods(Goods goods) {
        return goodsService.modify(goods);
    }

    //根据Id查询单个商品
    @RequestMapping("/query/agoods")
    public Response getGoodsById(Long id) {
        Goods goods = goodsService.queryById(id);
        if (goods == null) return new Response().badReturn("failed");
        return new Response().easyReturn(goods);
    }

    //新增一个订单
    @RequestMapping(value = "/add/record", method = RequestMethod.POST)
    public Response addRecord(Orders orders) {
        if (!orderService.checkParams(orders))
            return new Response().badReturn("参数不合法");
        if (orderService.save(orders)) {
            return new Response().easyReturn("success");
        }
        return new Response().badReturn("failed");
    }

    //删除一个订单
//    @RequestMapping("/delete/record")
//    public Response deleteRecord(Integer buyId) {
//        if (orderService.deleteRecord(buyId)) {
//            return new Response().easyReturn("success");
//        }
//        return new Response().badReturn("failed");
//    }

    //根据用户的id查询所有的订单
    @RequestMapping("/query/user/records")
    public Response UserQueryRecords(Long id, int index, int size) {
        Page<Orders> list = null;
        list = orderService.userQuery(id, index, size);
        return new Response().easyReturn(list.getRecords());
    }

    //根据商家的id查询所有订单
    @RequestMapping("/query/business/records")
    public Response BusinessQueryRecords(Long id, int index, int size) {
        Page<Orders> list = null;
        list = orderService.BusinessQuery(id, index, size);
        return new Response().easyReturn(list.getRecords());
    }

    //根据id查询单个订单
    @RequestMapping("/query/record")
    public Response QueryById(Long id) {
        Orders orders = new Orders();
        orders = orderService.selectById(id);
        return new Response().easyReturn(orders);
    }

    //用户点击完成订单
    @RequestMapping("/finish/record")
    public Response finishRecord(Long id) {
        if (orderService.arrive(id)) {
            return new Response().easyReturn("success");
        } else
            return new Response().easyReturn("failed");
    }

    //批量完成
    @RequestMapping("/finish/records")
    public Response finishRecords(String ids) {
        if (!orderService.arriveMore(ids))
            return new Response().badReturn("failed");
        return new Response().easyReturn("success");
    }

    //批量添加
    @RequestMapping("/add/records")
    public Response addRecords(Orders entity) {
        if (!orderService.addMore(entity))
            return new Response().badReturn("failed");
        return new Response().easyReturn("success");
    }

    //批量删除
    @RequestMapping(value = "/delete/records",method = RequestMethod.POST)
    public Response deleteRecords(String ids) {
        if (!orderService.deleteMore(ids))
            return new Response().badReturn("failed");
        return new Response().easyReturn("success");
    }

    @RequestMapping("/query/business")
    public Response QueryBusiness(int index, int size) {
        return new Response().easyReturn(userService.getAllBusiness(index,size).getRecords());
    }

    @RequestMapping("/delete/Business")
    public Response DeleteBusiness(Integer BusinessID) {
        return new Response().easyReturn(rootService.DeleteBusiness(BusinessID));
    }
    @RequestMapping("/add/businessman")
    public Response addBusiness(User user) {
         user.setRole(1);
         return userService.insertUser(user);
    }

}
