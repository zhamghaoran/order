package com.order.Controller;

import com.order.Service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RootController {
    @Autowired
    private RootService rootService;

}
