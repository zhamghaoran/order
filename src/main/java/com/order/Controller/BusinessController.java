package com.order.Controller;

import com.order.Service.BusinessService;
import com.order.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @RequestMapping("/add/goods")
    public Response addGoods(String token,
                             String name,
                             Integer price,
                             String description,
                             String picture,
                             Integer businessID) {

        return businessService.addGoods(name,price,description,picture,businessID);

    }
}
