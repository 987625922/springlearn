package com.wind.spring.web;

import com.wind.spring.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping("/logined")
    public String logined(@RequestParam("userName")String account,
                          @RequestParam("password")String pwd,
                          HttpSession httpSession){
        if (account.equals("cs")){
            User user = new User();
            user.setUserName("cs");
            httpSession.setAttribute("user_session",user);
            return "redirect:user/search";
        }else {
            return "redirect:login";
        }
    }
}
