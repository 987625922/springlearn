package com.wind.spring.data.hibernate;

import com.wind.spring.bean.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * hibernate的简单使用
 */
public class HibernateMain {

    private static Logger logger = LoggerFactory.getLogger(HibernateMain.class);

    public static void main(String[] args) {
        Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession(); // 相当于得到一个Connection
        // 开启事务
        session.beginTransaction();

        // 根据业务来编写代码
        Query query = session.createQuery("from Book"); // HQL语句，它类似于SQL语句
        List<Book> list = query.list();

        // 事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        for (Book book : list) {
            logger.debug("hibernate使用输出：" + book);
        }
    }
}
