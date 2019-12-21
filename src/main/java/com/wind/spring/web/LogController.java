package com.wind.spring.web;

import com.wind.spring.util.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * slf4j+logback的使用
 */
@RestController
@RequestMapping("/api/log")
public class LogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("log")
    public Object log() {
        LOGGER.debug("======debug level message=====");
        LOGGER.info("======info level message=====");
        LOGGER.warn("======warn level message=====");
        LOGGER.error("======error level message=====");
        return new JsonData(200, "", "log的使用学习");
    }

}
