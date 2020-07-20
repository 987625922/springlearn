package org.learn.hibernate.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.learn.hibernate.bean.HBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 书本实体类的dao
 *
 * @author LL
 */
@Slf4j
@Repository
@Transactional
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
     * get会马上取值，load在使用的时候才会取值
     * load的使用
     * 当调用load()方法的时候会返回一个目标对象的代理对象，
     * 在这个代理对象中只存储了目标对象的ID值，
     * 只有当调用除ID值以外的属性值的时候才会发出SQL查询的。
     *
     * @param id
     * @return
     */
    public HBook loadById(long id) {
//        log.debug("hibernate中get和load的使用",getSession().load(HBook.class,id));
        return getSession().load(HBook.class, id);
    }

    /**
     * mysql语句的使用
     *
     * @return
     */
    public List<HBook> listSQL() {
        NativeQuery sqlQuery =
                getSession().createSQLQuery("select * from h_book");
        sqlQuery.addEntity(HBook.class);
        List<HBook> list = sqlQuery.list();
        return list;
    }


    /**
     * 多对多延迟加载
     * <p>
     * 只有在使用到多方的类时才会发出sql查询
     *
     * @param id
     * @return
     */
    public HBook lazyMoreToMoreLoad(long id) {
        return getSession().get(HBook.class, id);
    }

    /**
     * =========== jpql
     * 1.里面不能出现表名,列名,只能出现java的类名,属性名，区分大小写
     * 2.出现的sql关键字是一样的意思,关键字不区分大小写
     * 3.不能写select * 要写select 别名
     */

    /**
     * jqpl获取所有
     *
     * @return
     */
    public List<HBook> selectAllList() {
        return getSession().createQuery("from HBook").list();
    }

    /**
     * jqpl限制搜索
     *
     * @param id
     * @return
     */
    public HBook selectById(long id) {
        Query query = getSession()
                .createQuery("from HBook where id=?0");
        query.setParameter(0, id);
        return (HBook) query.uniqueResult();
    }

    /**
     * hql排序
     *
     * @return
     */
    public List<HBook> selectSort() {
        Query query =
                getSession().createQuery("from HBook order by id desc");
        return query.list();
    }

    /**
     * 查询id在...和...之间的书本（between的使用）
     *
     * @return
     */
    public List<HBook> selectBetweenAnd(Long id1, Long id2) {
        return getSession().createQuery("from HBook WHERE id BETWEEN ?1 AND ?2")
                .setParameter(1, id1).setParameter(2, id2).list();
    }

    /**
     * Like的使用
     */
    public List<HBook> selectLike(String params) {
        return getSession().createQuery("FROM HBook WHERE name LIKE ?1")
                .setParameter(1, params).list();
    }

    /**
     * 查询没有作者的课本
     * 1）只有集合变量才能使用size【双向多对多的两方、双向一对多/多对一的一方】
     * 2）size可以用来作为判断条件、可以用来排序、可以用作查询结果
     */
    public List<HBook> selectSize() {
        return getSession()
                .createQuery("FROM HBook h WHERE h.hAuthors.size = 0")
                .list();
    }

    /**
     * 隐式内连接的使用
     *
     * @return
     */
    public List<HBook> hideInnerJoin() {
        return
                getSession()
                        .createQuery("FROM HBook h WHERE h.hAuthors.size > 0")
                        .list();
    }

    /**
     * 显式内连接的使用
     */
    public List<HBook> innerJoin() {
        return getSession()
                .createQuery("FROM HBook h INNER JOIN " +
                        "h.hAuthors a WHERE a.hBooks.size > 0").list();
    }

    /**
     * 使用聚合函数和分组
     */
    public List<Object[]> aggregateAndGroup() {
        return getSession()
                .createQuery("SELECT max(h.number),name " +
                        "FROM HBook h GROUP BY h.name")
                .list();
    }

    /**
     * 子查询
     *
     * @return
     */
    public List<HBook> chileSelect() {
        return getSession().createQuery("FROM HBook h WHERE " +
                "h.number > (SELECT AVG(h.number) FROM HBook)").list();
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
        Query query = getSession()
                .createQuery("select count(id) from HBook");
        Long num = (Long) query.uniqueResult();
        return num;
    }

    /**
     * hql 获取指定列名
     *
     * @return
     */
    public List<Object[]> hqlJoinSelect() {
        return getSession()
                .createQuery("SELECT h.name from HBook h")
                .list();
    }
}
