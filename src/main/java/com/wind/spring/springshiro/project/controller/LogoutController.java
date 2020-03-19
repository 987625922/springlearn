package com.wind.spring.springshiro.project.controller;


import com.wind.spring.springshiro.project.domain.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {


    @RequestMapping("/logout")
    public JsonData findMyPlayRecord() {

        Subject subject = SecurityUtils.getSubject();

        if (subject.getPrincipals() != null) {

        }

        SecurityUtils.getSubject().logout();

        return JsonData.buildSuccess("logout成功");

    }

}
