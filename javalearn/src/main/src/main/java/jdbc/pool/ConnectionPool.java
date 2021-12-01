package jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc数据链接池
 */
public class ConnectionPool {

    List<Connection> cs = new ArrayList<Connection>();

    int size;

    /**
     * 初始化连接数量
     * @param size 连接数量
     */
    public ConnectionPool(int size) {
        this.size = size;
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        //这里恰恰不能使用try-with-resource的方式，因为这些连接都需要是"活"的，不要被自动关闭了
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                Connection c = DriverManager
                        .getConnection("jdbc:mysql://127.0.0.1:3306/learn?characterEncoding=UTF-8&serverTimezone=GMT%2B8",
                                "root", "root");
                cs.add(c);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从list中获取数据库连接
     * @return
     */
    public synchronized Connection getConnection() {
        while (cs.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /**
         * 从list中取出一个连接并清除它在list中的引用
         */
        Connection c = cs.remove(0);
        return c;
    }

    /**
     * 把数据库连接还给list
     * @param c
     */
    public synchronized void returnConnection(Connection c) {
        cs.add(c);
        this.notifyAll();
    }

}
