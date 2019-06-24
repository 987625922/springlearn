package com.wind.spring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        String sql = "select * from book";
        Connection con = null;
        Statement stmt = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接对象
        con = DriverManager.getConnection("jdbc:mysql:///ssm?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "root");
        //执行命令对象
        stmt = con.createStatement();
        stmt.execute(sql);

        stmt.close();
        con.close();

    }
}
