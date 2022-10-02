package com.order.Controller;

import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.Orders;
import com.order.Dao.pojo.User;
import com.order.Service.BusinessService;
import com.order.Service.OrderService;
import com.order.Service.RootService;
import com.order.Service.UserService;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RootService rootService;
    @Autowired
    private BusinessService businessService;

    /**
     * 待定，需要修改
     * @param UID
     * @return
     */
    @RequestMapping("/login")
    public Response GetUserRole(String UID) {
        User user = userService.FindUserByUID(UID);
        Map<String ,String> map = new HashMap<>();
        if (user.getRole() == 1) {
            map.put("role","1");
            return new Response().easyReturn(map);
        }
        else if (user.getRole() == 2) {
            map.put("role","2");
            return new Response().easyReturn(map);
        }
        else if (user.getRole() == 0) {
            map.put("role","3");
            return new Response().easyReturn(map);
        }
        else {
            return new Response().badReturn("UID 错误");
        }
    }

    /**
     * 添加商品
     * @param UID
     * @param goods
     * @return
     */
    @RequestMapping("/add/good")
    public Response addGoods(String UID, Goods goods) {
        User user = userService.FindUserByUID(UID);
        if (user.getRole() != 1)
            return new Response().badReturn("UID 错误");
        return businessService.addGoods(UID,goods);
    }

    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @RequestMapping("/delete/goods")
    public Response deleteGoods(Integer goodsId) {
        if (businessService.findGoodsById(goodsId) == null) {
            return new Response().badReturn("商品ID错误");
        }
        businessService.deleteGoods(goodsId);
        return new Response().easyReturn("true");
    }


    @Autowired
    OrderService orderService;

    //新增一个订单
    @RequestMapping("/add/record")
    public Boolean addRecord(Orders orders) {
        if(orderService.save(orders)) {
            return true;
        }
        return false;
    }

    //删除一个订单
    @RequestMapping("/delete/record")
    public Boolean deleteRecord(Long id) {
        if(orderService.removeById(id)) {
            return true;
        }
        return false;
    }

    //根据用户的id查询所有的订单
    @RequestMapping("/query/user/records")
    public List<Orders> UserQueryRecords(Long id){
        List<Orders> list = null;
        list = orderService.userQuery(id);
        return list;
    }

    //根据商家的id查询所有订单
    @RequestMapping("/query/business/records")
    public List<Orders> BusinessQueryRecords(Long id){
        List<Orders> list = null;
        list = orderService.BusinessQuery(id);
        return list;
    }

    //根据id查询单个订单
    @RequestMapping("/query/record")
    public Orders QueryById(Long id){
        Orders orders = new Orders();
        orders = orderService.selectById(id);
        return orders;
    }

    //用户点击完成订单
    @RequestMapping("/finish/record")
    public Boolean finishRecord(Long id){
        try {
            if(orderService.arrive(id)){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //批量完成
    @RequestMapping("/finish/records")
    public Boolean finishRecords(String ids){
        if(!orderService.arriveMore(ids))return false;
        return true;
    }
    //批量添加
    @RequestMapping("/add/records")
    public Boolean addRecords(Long sellId,String goodsIds,Long buyId){
        if(!orderService.addMore(sellId,goodsIds,buyId))return false;
        return true;
    }
    //批量删除
    @RequestMapping("/delete/records")
    public Boolean deleteRecords(String ids){
        if(!orderService.deleteMore(ids))return false;
        return true;
    }
    @RequestMapping("/query/business")
    public Response QueryBusiness() {
        return new Response().easyReturn(businessService.getAllBusiness());
    }
    @RequestMapping("/delete/Business")
    public Response DeleteBusiness(Integer BusinessID) {
        return new Response().easyReturn(rootService.DeleteBusiness(BusinessID));


    }
}
