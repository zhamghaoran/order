package com.order.Controller;

import com.order.Service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
}
