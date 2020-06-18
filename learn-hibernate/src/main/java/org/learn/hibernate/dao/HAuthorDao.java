package org.learn.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.learn.hibernate.bean.HAuthor;
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

    public HAuthor get(long id){
        return getSession().get(HAuthor.class,id);
    }

}