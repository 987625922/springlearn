package http.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * servlet获取共享对象
 */
public class GetShareRequser extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = this.getServletContext();
        HttpSession session = req.getSession();
        String ctx_value = (String) servletContext.getAttribute("ctx_name");
        String session_value = (String) session.getAttribute("session_name");
        String request_value = (String) req.getAttribute("request_name");

        System.out.println("ctx_value:" + ctx_value);
        System.out.println("session_value:" + session_value);
        System.out.println("request_value:" + request_value);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
