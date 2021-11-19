package com.learn.elastic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/elastic")
public class ProductController {

    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    public Object simpleSearch() {
        return null;
    }
}
