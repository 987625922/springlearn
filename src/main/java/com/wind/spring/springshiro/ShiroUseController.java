package com.wind.spring.springshiro;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * shiro用的controller
 */
@Controller
@RequestMapping("/api/admin")
public class ShiroUseController {

    /**
     * RequiresRoles为权限控制
     * 表示需要admin或者editer的身份才可以访问这个接口
     * Logical.OR 表示或者 （表示身份选择）
     * Logical.AND 表示和
     *
     * @return
     */
    @RequiresRoles(value = {"admin", "editer"}, logical = Logical.OR)
    @RequestMapping("getlist")
    public Object getlist1() {
        return null;
    }

    /**
     * RequiresPermissions为权限控制
     * 表示需要admin:add或者editer:add的权限才可以访问
     *
     * @return
     */
    @RequiresPermissions(value = {"admin:add", "editer:add"}, logical = Logical.OR)
    @RequestMapping("getlist1")
    public Object getlist2() {
        return null;
    }
}
