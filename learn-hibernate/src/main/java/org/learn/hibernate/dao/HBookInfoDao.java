package org.learn.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.learn.hibernate.bean.HBookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HBookInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(HBookInfo hBookInfo) {
        getSession().save(hBookInfo);
    }
}
