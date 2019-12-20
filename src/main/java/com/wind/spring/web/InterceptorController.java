package com.wind.spring.web;

import com.wind.spring.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 拦截器的学习
 * <p>
 */
@Controller
@RequestMapping("/api/interceptor")
public class InterceptorController {

    /**
     * 跳转到登录界面
     *
     * @return
     */
    @RequestMapping("/tologin")
    public String login() {
        return "user/login";
    }

    /**
     * 登录界面表单请求的登录接口
     *
     * @param account
     * @param pwd
     * @param httpSession
     * @return
     */
    @RequestMapping("/login")
    public String logined(@RequestParam("userName") String account,
                          @RequestParam("password") String pwd,
                          HttpSession httpSession) {
        if (account.equals("cs")) {
            User user = new User();
            user.setUserName("cs");
            httpSession.setAttribute("user_session", user);
            return "redirect:userinfo";
        } else {
            return "redirect:login";
        }
    }

    /**
     * 在spring-web.xml中配置了对/api/interceptor/* 这个链接下的拦截（拦截器为LoginInterceptor）
     *
     * @return
     */
    @RequestMapping("/userinfo")
    public String userinfo() {
        System.out.println("2:interceptor===>search");
//        return new JsonData(200, "", "InterceptorController类search方法，使用拦截器成功");
        return "user/userinfo";
    }
}
