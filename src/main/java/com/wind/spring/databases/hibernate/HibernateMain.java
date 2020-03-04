package com.wind.spring.databases.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


/**
 * hibernate的简单使用
 */
@Slf4j
public class HibernateMain {

//    public static void main(String[] args) {
////        insertBook();
//        selectBook();
//    }
//
//    public static void selectBook() {
//        Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
//        SessionFactory sessionFactory = config.buildSessionFactory();
//        Session session = sessionFactory.openSession(); // 相当于得到一个Connection
//
//        // 根据业务来编写代码
//        Query query = session.createQuery("FROM HBook"); // HQL语句，它类似于SQL语句
//        List<HBook> list = query.list();
//
//        session.close();
//        sessionFactory.close();
//
//        for (HBook book : list) {
//            log.debug("hibernate使用输出：" + book);
//        }
//    }
//
//    public static void insertBook() {
//        //创建对象
//        HBook book = new HBook();
//        book.setNumber("121");
//        book.setName("测试");
//
//        //获取加载配置管理类
//        Configuration configuration = new Configuration();
//
//        //不给参数就默认加载hibernate.cfg.xml文件，
//        configuration.configure();
//
//        //创建Session工厂对象
//        SessionFactory factory = configuration.buildSessionFactory();
//
//        //得到Session对象
//        Session session = factory.openSession();
//
//        //使用Hibernate操作数据库，都要开启事务,得到事务对象
//        Transaction transaction = session.getTransaction();
//
//        //开启事务
//        transaction.begin();
//
//        //把对象添加到数据库中
//        session.save(book);
//
//        //提交事务
//        transaction.commit();
//
//        //关闭Session
//        session.close();
//
//        factory.close();
//    }

}
