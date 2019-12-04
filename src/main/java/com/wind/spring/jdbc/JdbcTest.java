package com.wind.spring.jdbc;

import java.sql.*;

/**
 * jdbc的使用
 *
 */
public class JdbcTest {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            String sql = "select * from book";
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接对象
            con = DriverManager.getConnection("jdbc:mysql:///ssm?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "root");
            //执行命令对象
            stmt = con.createStatement();
            //执行sql语句,拿到结果集
            resultSet = stmt.executeQuery("SELECT * FROM book");

            //遍历结果集，得到数据
            while (resultSet.next()) {

                System.out.println(resultSet.getString(1));

                System.out.println(resultSet.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (con != null){
                try {
                    con.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
