package com.learn.rabbitmq.controller;

import com.learn.rabbitmq.service.OmsPortalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单管理Controller
 */
@Controller
@RequestMapping("/order")
public class OmsController {

    @Autowired
    private OmsPortalOrderService service;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public Object generateOrder() {
        return service.generateOrder();
    }

}
