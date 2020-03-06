package com.wind.spring.databases.hibernate.dao;

import com.wind.spring.databases.hibernate.bean.HBook;
import com.wind.spring.databases.hibernate.bean.HBookOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HBookOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(HBookOrder hBookOrder){
        getSession().save(hBookOrder);
    }

}
