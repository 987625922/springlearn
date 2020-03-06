package com.wind.spring.databases.hibernate.dao;

import com.wind.spring.databases.hibernate.bean.HAuthor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HAuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(HAuthor hAuthor){
        getSession().save(hAuthor);
    }

}
