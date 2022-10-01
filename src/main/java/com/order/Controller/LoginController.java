package com.order.Controller;

import com.order.Dao.pojo.Business;
import com.order.Dao.pojo.Root;
import com.order.Dao.pojo.User;
import com.order.Service.BusinessService;
import com.order.Service.RootService;
import com.order.Service.UserService;
import com.order.util.Response;
import com.order.util.TokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private RootService rootService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response Login(String username, String password) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        Map<String, Object> re = new HashMap<>();
        User login = userService.Login(username, password);
        if (login != null) {
            re.put("CUSTOMER", "0");
            String token = new TokenCheck().addToken(login, 0);
            re.put("token", token);
            re.put("message", login);
            result.put("enum", re);
            return new Response().easyReturn(result);
        }
        Root root = rootService.Login(username, password);
        if (root != null) {
            String token = new TokenCheck().addToken(root, 2);
            re.put("OPERATOR", "2");
            re.put("token", token);
            re.put("message", root);
            result.put("enmu", re);
            return new Response().easyReturn(result);
        }
        Business business = businessService.Login(username, password);
        if (business != null) {
            String token = new TokenCheck().addToken(business,1);
            re.put("token", token);
            re.put("BUSINESSMAN", "1");
            re.put("message", business);
            result.put("enum", re);
            return new Response().easyReturn(result);
        }
        return new Response().easyReturn("查无此人");
    }

    @RequestMapping(value = "/register",method = RequestMethod.PATCH)
    public Response Register(String username, String password, Integer enumCode) {
        String register = "";
        if (enumCode == 1) {
            register = businessService.Register(username, password);
        }
        if (enumCode == 2) {
            register = userService.Register(username, password);
        }
        if (register.equals("success")) {
            return new Response().easyReturn(register);
        }else {
            return new Response().badReturn(register);
        }

    }
}
