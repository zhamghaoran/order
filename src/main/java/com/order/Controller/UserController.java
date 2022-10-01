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
        return null;
    }

}
