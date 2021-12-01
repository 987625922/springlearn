package http.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * servlet的使用
 */
public class HttpServlet extends javax.servlet.http.HttpServlet {

    public HttpServlet() {
        System.out.println("httpServlet...");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("HttpServlet init...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        super.doGet(req, resp);
//        System.out.println(req.getParameterNames());

        System.out.println("===> context-param");
        String paramValue = this.getServletContext().getInitParameter("paramMame");
        System.out.println("context-param:" + paramValue);
        Enumeration pNames = req.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = req.getParameter(name);
            System.out.println(name + "=" + value);
        }
        System.out.println("===> context-param end");

        System.out.println("username：" + req.getParameter("username"));

        System.out.println("获取checkbox数据");
        String[] check = req.getParameterValues("hobby");
        for (int i = 0; i < check.length; i++) {
            System.out.println("checkbox" + i + ":" + check[i]);
        }

        //servlet的请求重定向
//        resp.sendRedirect("www.baidu.com");
        //请求包含
//        req.getRequestDispatcher("myservlet").forward(req, resp);

        /*  servlet共享变量  */
        /*
         * 1. ServletContext
         * 2.HttpSession
         * 3.HttpServletRequest
         * */
        this.getServletContext().setAttribute("ctx_name", "ctx_value");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("session_name", "session_value");

        req.setAttribute("request_name", "request_value");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        System.out.println(req.getParameterNames());
        System.out.println(req.getParameter("username"));
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy...");
    }
}
