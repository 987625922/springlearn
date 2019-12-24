package com.wind.spring.data.hibernate;

import com.wind.spring.bean.AllPerson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession(); // 相当于得到一个Connection
        // 开启事务
        session.beginTransaction();

        // 根据业务来编写代码
        Query query = session.createQuery("from AllPerson"); // HQL语句，它类似于SQL语句
        List<AllPerson> list = query.list();
        System.out.println(list);

        // 事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

        for (AllPerson allPerson:list){
            System.out.println("hibernate使用输出："+allPerson);
        }
    }
}
