package com.order.Controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.order.Dao.pojo.Address;
import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.*;
import com.order.util.HttpRequest;
import com.order.util.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;
    private final RootService rootService;
    private final BusinessService businessService;
    private final OrderService orderService;
    final
    GoodsService goodsService;
    public UserController(UserService userService, RootService rootService, BusinessService businessService, OrderService orderService, GoodsService goodsService) {
        this.userService = userService;
        this.rootService = rootService;
        this.businessService = businessService;
        this.orderService = orderService;
        this.goodsService = goodsService;
    }


    @RequestMapping("/wechat")
    public Response loginWechat(String code) {
        String params = "grant_type=" + "authorization_code" + "&secret=" + "40cbf33b1ceaa4ce697af92929e9a96f" + "&appid="+ "wx51d09fce06765763" + "&js_code=" + code;
        String sendGet = new HttpRequest().sendGet("https://api.weixin.qq.com/sns/jscode2session?", params);
        JSONObject json = JSONObject.parseObject(sendGet);
        return new Response().easyReturn(json);

    }
    /**
     * 待定，需要修改
     *
     */
    @RequestMapping("/login")
    public Response GetUserRole(String code) {
        String params = "grant_type=" + "authorization_code" + "&secret=" + "40cbf33b1ceaa4ce697af92929e9a96f" + "&appid="+ "wx51d09fce06765763" + "&js_code=" + code;
        String sendGet = new HttpRequest().sendGet("https://api.weixin.qq.com/sns/jscode2session?", params);
        JSONObject json = JSONObject.parseObject(sendGet);
        String errcode = json.getString("errcode");
        if (errcode != null) {
            Map<String,String> map = new HashMap<>();
            map.put("errcode", errcode);
            map.put("errmsg",json.getString("errmsg"));
            return new Response().badReturn(map);
        }
        String UID = json.getString("openid");
        User user = userService.FindUserByUID(UID);
        if (user == null) {
            return userService.Register(UID);
        }
        Map<String, String> map = new HashMap<>();
        map.put("openid",UID);
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
        Page<Goods> list;
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

    //根据用户的id查询所有的订单
    @RequestMapping("/query/user/records")
    public Response UserQueryRecords(Long id, int index, int size) {
        Page<Orders> list;
        list = orderService.userQuery(id, index, size);
        return new Response().easyReturn(list.getRecords());
    }

    //根据商家的id查询所有订单
    @RequestMapping("/query/business/records")
    public Response BusinessQueryRecords(Long id, int index, int size) {
        Page<Orders> list;
        list = orderService.BusinessQuery(id, index, size);
        return new Response().easyReturn(list.getRecords());
    }

    //根据id查询单个订单
    @RequestMapping("/query/record")
    public Response QueryById(Long id) {
        Orders orders;
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
    @RequestMapping("/get/address")
    public Response getAddress() {
        return userService.selectAddress();
    }
    @RequestMapping(value = "/add/address",method = RequestMethod.POST)
    public Response addAddress(Address address) {
        return userService.addAddress(address);
    }
    @RequestMapping(value = "/delete/address",method = RequestMethod.POST)
    public Response deleteAddress(String Id) {
        return userService.deleteAddress(Id);
    }

}
