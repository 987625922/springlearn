package com.wind.spring.web.controller;

import com.wind.spring.bean.Book;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/params")
public class ParamController {

    /*** ==================  GET请求参数获取 ======================= ***/
    /**
     * get请求参数，一般都是直接挂在请求的url上，所以获取这些参数还是比较简单的
     */

    /**
     * javax.servlet.ServletRequest#getParameter 来获取对应的参数
     * http://localhost:8080/params/get1?user=coco&&password=12
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/get1", method = RequestMethod.GET)
    public String getParam1(HttpServletRequest request) {
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        return "get1 user: " + user + " pwd: " + password;
    }

    /**
     * 直接方法参数获取
     * <p>
     * http://localhost:8080/params/get2?user=coco&&password=12
     *
     * @param user
     * @param password
     * @return
     */
    @RequestMapping(path = "/get2", method = RequestMethod.GET)
    public String getParam2(String user, String password) {
        return "getParam2 user: " + user + " pwd: " + password;
    }

    /**
     * RequestParam注解方式获取请求参数
     * <p>
     * http://localhost:8080/params/get3?user=coco&&password=12
     *
     * @param username
     * @param pwd
     * @return
     */
    @RequestMapping(path = "get3", method = RequestMethod.GET)
    public String getParam3(@RequestParam("user") String username,
                            @RequestParam("password") String pwd) {
        return "getParam3 user: " + username + " pwd: " + pwd;
    }

    /**
     * Bean方式获取参数
     * <p>
     * http://localhost:8080/params/get4?id=12&name=bookname
     *
     * @param book
     * @return
     */
    @RequestMapping(path = "get4", method = RequestMethod.GET)
    public String req4(Book book) {
        return "get4 book: " + book;
    }

    /**
     * Path参数
     * http://localhost:8080/params/get5/bookname/info
     *
     * @param userName
     * @return
     */
    @RequestMapping(path = "get5/{userName}/info", method = RequestMethod.GET)
    public String req6(@PathVariable(name = "userName") String userName) {
        return "get5 user: " + userName;
    }

    /** =================================== get ============= */

    /** =========================== POST请求参数获取 ========================= */

    /**
     * 对于HttpServletReuqest方式获取参数时，get和post没什么区别
     * 1.http://localhost:8080/params/post1?user=coco&password=123
     * <p>
     * 2.http://localhost:8080/params/post1
     * Content-Type: application/x-www-form-urlencoded
     * 在x-www-form-urlencoded
     * user=12
     * password=123
     */
    @PostMapping("/post1")
    public String post1(HttpServletRequest request) {
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        return "post1 user: " + user + " pwd: " + password;
    }

    /***
     * 1.http://localhost:8080/params/post1?user=coco&password=123
     *
     * 2.http://localhost:8080/params/post1
     * Content-Type: application/x-www-form-urlencoded
     * 在x-www-form-urlencoded
     *  user=12
     *  password=123
     *
     * @param user
     * @param password
     * @return
     */
    @PostMapping("/post2")
    public String post2(String user, String password) {
        return "post2 user: " + user + " pwd: " + password;
    }

    /**
     * 1.http://localhost:8080/params/post1?user=coco&password=123
     * <p>
     * 2.http://localhost:8080/params/post1
     * Content-Type: application/x-www-form-urlencoded
     * 在x-www-form-urlencoded
     * user=12
     * password=123
     *
     * @param username
     * @param pwd
     * @return
     */
    @PostMapping("/post3")
    public String post3(@RequestParam("user") String username,
                        @RequestParam("password") String pwd) {
        return "post3 user: " + username + " pwd: " + pwd;
    }

    /**
     * 1.http://localhost:8080/params/post4?name=coco&id=123
     * <p>
     * 2.http://localhost:8080/params/post4
     * Content-Type: application/x-www-form-urlencoded
     * 在x-www-form-urlencoded
     * id=12
     * name=123
     *
     * @param book
     * @return
     */
    @PostMapping(path = "post4")
    public String post4(Book book) {
        return "post4 book: " + book;
    }

    /** =========================== post ========================= */

}
