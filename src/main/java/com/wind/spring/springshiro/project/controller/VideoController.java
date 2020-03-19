package com.wind.spring.springshiro.project.controller;


import com.wind.spring.springshiro.project.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("video")
public class VideoController {


    @RequestMapping("/update")
    public JsonData updateVideo() {

        return JsonData.buildSuccess("video更新成功");

    }

}