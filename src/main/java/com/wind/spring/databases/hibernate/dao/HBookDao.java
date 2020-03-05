package com.wind.spring.databases.hibernate.dao;

import com.wind.spring.databases.hibernate.bean.HBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(HBook book){
        getSession().save(book);
    }

    public List<HBook> list(){
        Query query = getSession().createQuery("FROM hbook"); // HQL语句，它类似于SQL语句
        List<HBook> list = query.list();
        return list;
    }

}
