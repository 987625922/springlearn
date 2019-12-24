package com.wind.spring.web.controller;

import com.wind.spring.util.JsonData;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * cookie和session的使用
 */
@RestController
@RequestMapping("/api/cookie")
public class CookieController {

    private static final String USERNAME = "USERNAME";


    /**
     * 如果有cookie就获取cookie输出，没有就为他添加
     *
     * @param username 通过注解的方式获取cookie
     * @param response
     * @return
     */
    @RequestMapping("cookie")
    public Object addCookie(@CookieValue(value = USERNAME,required = false) String username, HttpServletResponse response) {
        if (username != null) {
            return new JsonData(200, "",
                    "该用户已有cookie，用户名为" + username);
        }

        String uuid = UUID.randomUUID().toString();
        Cookie uidCk = new Cookie(USERNAME, uuid);
        uidCk.setMaxAge(-1);
        response.addCookie(uidCk);
        return new JsonData(200, "", "该用户还没有cookie，已为他添加用户名为" + uuid);
    }
    /**
     * 非注解方式获取cookie
     * public Object addCookie(HttpServletRequest request, HttpServletResponse response) {
     * Cookie[] cookies = request.getCookies();
     * if (cookies != null) {
     * for (Cookie cookie : cookies) {
     * if (cookie.getName().equals(USERNAME)) {
     * return new JsonData(200, "",
     * "该用户已有cookie，用户名为" + cookie.getValue());
     * }
     * }
     * }
     * <p>
     * String uuid = UUID.randomUUID().toString();
     * Cookie uidCk = new Cookie(USERNAME, uuid);
     * uidCk.setMaxAge(-1);
     * response.addCookie(uidCk);
     * return new JsonData(200, "", "该用户还没有cookie，已为他添加用户名为" + uuid);
     * }
     */


    /**
     * 如果有session就获取session输出，没有就为他添加
     */
    @RequestMapping("session")
    public Object addSession(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(USERNAME);
        if (username == null) {
            String uuid = UUID.randomUUID().toString();
            request.getSession().setAttribute(USERNAME, uuid);
            return new JsonData(200, "", "该用户还没有session，已为他添加用户名为" + uuid);
        }
        return new JsonData(200, "",
                "该用户已有session，用户名为" + username);
    }

}
