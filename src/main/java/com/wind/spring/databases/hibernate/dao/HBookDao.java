package com.wind.spring.databases.hibernate.dao;

import com.wind.spring.databases.hibernate.bean.HBook;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void save(HBook book) {
        getSession().save(book);
    }

    public HBook get(long id) {
        return getSession().get(HBook.class, id);
    }

    public void saveTransaction(HBook book) {
        sessionFactory.getCurrentSession().save(book);
    }

    public List<HBook> list() {
        Query query = getSession().createQuery("FROM HBook"); // HQL语句，它类似于SQL语句
        List<HBook> list = query.list();
        return list;
    }

    public HBook getAndLoad(long id) {
//        log.debug("hibernate中get和load的使用",getSession().load(HBook.class,id));
        return getSession().get(HBook.class, id);
    }

    public List<HBook> listSQL() {
        NativeQuery sqlQuery = getSession().createSQLQuery("select * from h_book");
        sqlQuery.addEntity(HBook.class);
        List<HBook> list = sqlQuery.list();
        return list;
    }

    public HBook firstLevelCache(long id) {
        return getSession().get(HBook.class, id);
    }

    public HBook lazyLoad(long id) {
        HBook hBook = getSession().load(HBook.class, id);
        return hBook;
    }

    public HBook lazyMoreToMoreLoad(long id) {
        return getSession().get(HBook.class, id);
    }

    public List<HBook> hqlSelectAll() {
        Query query = getSession().createQuery("from HBook");
        return query.list();
    }

    public HBook hqlSelectConditionalQuery(long id) {
        Query query = getSession().createQuery("from HBook where id=?0");
        query.setParameter(0, id);
        return (HBook) query.uniqueResult();
    }

    public List<HBook> hqlprojectionQuery() {
        Query query = getSession().createQuery("select new HBook(id) from HBook");
        return query.list();
    }

    public List<HBook> hqlSort() {
        Query query = getSession().createQuery("from HBook order by id desc");
        return query.list();
    }

    public List<HBook> hqlPagin() {
        Query query = getSession().createQuery("from HBook");
        // * 开始索引 , startIndex 算法： startIndex = (pageNum - 1) * pageSize;
        // *** pageNum 当前页（之前的 pageCode）
        query.setFirstResult(0);
        // * 每页显示个数 ， pageSize
        query.setMaxResults(2);
        return query.list();
    }

    public Long hqlAggregate(){
        Query query = getSession().createQuery("select count(id) from HBook");
        Long num = (Long) query.uniqueResult();
        return num;
    }

    public List<Object[]> hqlJoinSelect(){
        return getSession().createQuery("from HBook h left outer join h.hAuthors").list();
    }

}
