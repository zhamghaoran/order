package com.order.Controller;

import com.order.Dao.pojo.Goods;
import com.order.Dao.pojo.User;
import com.order.Service.BusinessService;
import com.order.Service.RootService;
import com.order.Service.UserService;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/add/good")
    public Response addGoods(String UID, Goods goods) {
        User user = userService.FindUserByUID(UID);
        if (user.getRole() != 1)
            return new Response().badReturn("UID 错误");
        return businessService.addGoods(UID,goods);
    }
    @RequestMapping("/delete/goods")
    public Response deleteGoods(Integer goodsId) {
        if (businessService.findGoodsById(goodsId) == null) {
            return new Response().badReturn("商品ID错误");
        }
        businessService.deleteGoods(goodsId);
        return new Response().easyReturn("true");
    }

}
