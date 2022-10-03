package com.order.Controller;

import com.baomidou.mybatisplus.extension.api.R;
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
    @Autowired
    OrderService orderService;

    /**
     * 待定，需要修改
     *
     * @param UID
     * @return
     */
    @RequestMapping("/login")
    public Response GetUserRole(String UID) {
        User user = userService.FindUserByUID(UID);
        Map<String, String> map = new HashMap<>();
        if (user.getRole() == 1) {
            map.put("role", "1");
            return new Response().easyReturn(map);
        } else if (user.getRole() == 2) {
            map.put("role", "2");
            return new Response().easyReturn(map);
        } else if (user.getRole() == 0) {
            map.put("role", "3");
            return new Response().easyReturn(map);
        } else {
            return userService.Register(UID);
        }
    }

    /**
     * 添加商品
     *
     * @param UID
     * @param goods
     * @return
     */
    @RequestMapping("/add/good")
    public Response addGoods(String UID, Goods goods) {
        User user = userService.FindUserByUID(UID);
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
    @RequestMapping("/delete/goods")
    public Response deleteGoods(Integer goodsId) {
        if (businessService.findGoodsById(goodsId) == null) {
            return new Response().badReturn("商品ID错误");
        }
        businessService.deleteGoods(goodsId);
        return new Response().easyReturn("true");
    }


    //新增一个订单
    @RequestMapping("/add/record")
    public Response addRecord(Orders orders) {
        if (orderService.save(orders)) {
            return new Response().easyReturn("success");
        }
        return new Response().badReturn("failed");
    }

    //删除一个订单
    @RequestMapping("/delete/record")
    public Response deleteRecord(Long id) {
        if (orderService.removeById(id)) {
            return new Response().easyReturn("success");
        }
        return new Response().badReturn("failed");
    }

    //根据用户的id查询所有的订单
    @RequestMapping("/query/user/records")
    public Response UserQueryRecords(Long id) {
        List<Orders> list = null;
        list = orderService.userQuery(id);
        return new Response().easyReturn(list);
    }

    //根据商家的id查询所有订单
    @RequestMapping("/query/business/records")
    public Response BusinessQueryRecords(Long id) {
        List<Orders> list = null;
        list = orderService.BusinessQuery(id);
        return new Response().easyReturn(list);
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
        }
        return new Response().easyReturn("failed");
    }

    //批量完成
    @RequestMapping("/finish/records")
    public Response finishRecords(String ids) {
        if (!orderService.arriveMore(ids)) return new Response().badReturn("failed");
        return new Response().easyReturn("success");
    }

    //批量添加
    @RequestMapping("/add/records")
    public Response addRecords(Long sellId, String goodsIds, Long buyId) {
        if (!orderService.addMore(sellId, goodsIds, buyId)) return new Response().badReturn("failed");
        return new Response().easyReturn("success");
    }

    //批量删除
    @RequestMapping("/delete/records")
    public Response deleteRecords(String ids) {
        if (!orderService.deleteMore(ids)) return new Response().badReturn("failed");
        return new Response().easyReturn("success");
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
