package com.wind.spring.databases.hibernate.dao;

import com.wind.spring.databases.hibernate.bean.HBook;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class HBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(HBook book){
        getSession().save(book);
    }

    public HBook get(long id){
        return getSession().get(HBook.class,id);
    }

    public void saveTransaction(HBook book){
        sessionFactory.getCurrentSession().save(book);
    }

    public List<HBook> list(){
        Query query = getSession().createQuery("FROM hbook"); // HQL语句，它类似于SQL语句
        List<HBook> list = query.list();
        return list;
    }

    public HBook getAndLoad(long id){
//        log.debug("hibernate中get和load的使用",getSession().load(HBook.class,id));
        return getSession().get(HBook.class,id);
    }

    public List<HBook> listSQL(){
        NativeQuery sqlQuery = getSession().createSQLQuery("select * from h_book");
        sqlQuery.addEntity(HBook.class);
        List<HBook> list = sqlQuery.list();
        return list;
    }

    public HBook firstLevelCache(long id){
        return getSession().get(HBook.class,id);
    }

    public HBook lazyLoad(long id){
        HBook hBook = getSession().load(HBook.class,id);
        log.debug("在上面打断点，测试HBook是否查询出来了  "+hBook.getName());
        return hBook;
    }

    public HBook lazyMoreToMoreLoad(long id){
        return getSession().get(HBook.class,id);
    }
}
