package org.learn.hibernate.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.learn.hibernate.bean.HBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书本实体类的使用
 *
 * @author LL
 */
@Slf4j
@Repository
public class HBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 保存实体类
     *
     * @param book
     */
    public void save(HBook book) {
        getSession().save(book);
    }

    /**
     * 根据id获取实体类
     *
     * @param id
     * @return
     */
    public HBook get(long id) {
        return getSession().get(HBook.class, id);
    }

    /**
     * HQL获取列表
     *
     * @return
     */
    public List<HBook> list() {
        Query query = getSession().createQuery("FROM HBook"); // HQL语句，它类似于SQL语句
        List<HBook> list = query.list();
        return list;
    }

    /**
     * get会马上取值，load在使用的时候才会取值
     *
     * @param id
     * @return
     */
    public HBook getAndLoad(long id) {
//        log.debug("hibernate中get和load的使用",getSession().load(HBook.class,id));
        return getSession().get(HBook.class, id);
    }

    /**
     * mysql语句的使用
     *
     * @return
     */
    public List<HBook> listSQL() {
        NativeQuery sqlQuery = getSession().createSQLQuery("select * from h_book");
        sqlQuery.addEntity(HBook.class);
        List<HBook> list = sqlQuery.list();
        return list;
    }

    /**
     * get会马上取值，load在使用的时候才会取值
     *
     * @param id
     * @return
     */
    public HBook lazyLoad(long id) {
        HBook hBook = getSession().load(HBook.class, id);
        return hBook;
    }

    /**
     * 多对多lazy获取
     *
     * @param id
     * @return
     */
    public HBook lazyMoreToMoreLoad(long id) {
        return getSession().get(HBook.class, id);
    }

    /**
     * hql获取所有
     *
     * @return
     */
    public List<HBook> hqlSelectAll() {
        Query query = getSession().createQuery("from HBook");
        return query.list();
    }

    /**
     * hql限制搜索
     *
     * @param id
     * @return
     */
    public HBook hqlSelectConditionalQuery(long id) {
        Query query = getSession().createQuery("from HBook where id=?0");
        query.setParameter(0, id);
        return (HBook) query.uniqueResult();
    }

    /**
     * hql获取一个列名数据
     *
     * @return
     */
    public List<HBook> hqlprojectionQuery() {
        Query query = getSession().createQuery("select new HBook(id) from HBook");
        return query.list();
    }

    /**
     * hql排序
     *
     * @return
     */
    public List<HBook> hqlSort() {
        Query query = getSession().createQuery("from HBook order by id desc");
        return query.list();
    }

    /**
     * hql 分页
     *
     * @return
     */
    public List<HBook> hqlPagin() {
        Query query = getSession().createQuery("from HBook");
        // * 开始索引 , startIndex 算法： startIndex = (pageNum - 1) * pageSize;
        // *** pageNum 当前页（之前的 pageCode）
        query.setFirstResult(0);
        // * 每页显示个数 ， pageSize
        query.setMaxResults(2);
        return query.list();
    }

    /**
     * hql 获取总量
     *
     * @return
     */
    public Long hqlAggregate() {
        Query query = getSession().createQuery("select count(id) from HBook");
        Long num = (Long) query.uniqueResult();
        return num;
    }

    /**
     * hql 获取指定列名
     *
     * @return
     */
    public List<Object[]> hqlJoinSelect() {
        return getSession().createQuery("from HBook h left outer join h.hAuthors").list();
    }
}
