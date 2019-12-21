package com.wind.spring.web;

import com.wind.spring.util.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    private static final String USERNAME = "USERNAME";

    @RequestMapping("cookie")
    public Object addCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(USERNAME)) {
                    return new JsonData(200, "",
                            "该用户已有cookie，用户名为" + cookie.getValue());
                }
            }
        }

        String uuid = UUID.randomUUID().toString();
        Cookie uidCk = new Cookie(USERNAME, uuid);
        uidCk.setMaxAge(-1);
        response.addCookie(uidCk);
        return new JsonData(200, "", "该用户还没有cookie，已为他添加用户名为" + uuid);

    }

}
