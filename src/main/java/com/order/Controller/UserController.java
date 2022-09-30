package com.order.Controller;

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
        Map<String, Map<String, String>> result = new HashMap<>();
        Map<String, String> re = new HashMap<>();
        if (userService.FindUserByUID(UID) != null) {
            re.put("CUSTOMER", "0");
            result.put("enum", re);
            return new Response().easyReturn(result);
        }
        if (rootService.FindRootByUID(UID) != null) {
            re.put("OPERATOR", "2");
            result.put("enmu", re);
            return new Response().easyReturn(result);
        }
        if (businessService.FindBusinessByUID(UID) != null) {
            re.put("BUSINESSMAN", "1");
            result.put("enum", re);
            return new Response().easyReturn(result);
        }
        return new Response().easyReturn("查无此人");
    }

}
