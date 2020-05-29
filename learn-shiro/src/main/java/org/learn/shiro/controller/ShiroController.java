package org.learn.shiro.controller;

import org.learn.common.bean.User;
import org.learn.common.dto.JsonData;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * shiro用的controller
 */
@RestController
@RequestMapping("/api/shiro")
public class ShiroController {


    @RequestMapping("/not_permit")
    public Object noPermit() {
        return new JsonData(-100, null, "没有请求权限");
    }

    /**
     * RequiresRoles为权限控制
     * 表示需要admin或者editer的身份才可以访问这个接口
     * Logical.OR 表示或者 （表示身份选择）
     * Logical.AND 表示和
     */
    @RequiresRoles(value = {"admin", "editer"}, logical = Logical.OR)
    @RequestMapping("getlist")
    public Object getlist1() {
        return null;
    }

    /**
     * RequiresPermissions为权限控制
     * 表示需要admin:add或者editer:add的权限才可以访问
     */
    @RequiresPermissions(value = {"admin:add", "editer:add"}, logical = Logical.OR)
    @RequestMapping("getlist1")
    public Object getlist2() {
        return null;
    }


    @RequestMapping("/admin")
    public Object adminUserTest() {
        User user = new User();
        user.setBeanName("只有admin身份有权限请求的接口");
        return user;
    }
}
