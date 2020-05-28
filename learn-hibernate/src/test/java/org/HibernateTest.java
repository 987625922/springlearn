package org;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.hibernate.bean.HAuthor;
import org.learn.hibernate.bean.HBook;
import org.learn.hibernate.service.HBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-hibernate.xml"})
public class HibernateTest {


    @Autowired
    private HBookService service;

    /**
     * 插入数据
     */
    @Test
    public void save() {
        HBook hBook = new HBook();
        hBook.setName("书本名字");
        service.save(hBook);
    }

    /**
     * 测试事务
     */
    @Test
    public void testTransaction() {
        HBook hBook = new HBook();
        hBook.setName("测试xml事务");
        service.saveTransaction(hBook);
    }


    /**
     * 一对多关系映射 inverse
     * 级联 cascade
     */
    @Test
    public void testOneToMany() {
        service.saveBookAndOrderOneToMore();
    }

    /**
     * 一对一关系映射 inverse
     * 级联 cascade
     */
    @Test
    public void testOneToOne() {
        service.saveBookAndInfoOneToOne();
    }


    /**
     * 多对多关系映射 inverse
     * 级联 cascade
     */
    @Test
    public void testMoreToMore() {
        service.saveBookAndAuthorMoreToMore();
    }

    /**
     * get和load的使用
     */
    @Test
    public void getAndLoad() {
        log.info("hibernate中get和load的使用=》" + service.getAndLoad(1).toString());
    }

    /**
     * HQL
     * =======================================
     */
    @Test
    public void hqlSelectAll() {
        List<HBook> list = service.hqlSelectAll();
        for (HBook hBook : list) {
            log.info("hqlSelectAll ==> " + hBook.getName());
        }
        HBook hBook = list.get(0);
        for (HAuthor hAuthor : hBook.gethAuthors()) {
            log.info("hqlSelectAll ==> " + hAuthor.getName());
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void hqlSelectConditionalQuery() {
        HBook hBook = service.hqlSelectConditionalQuery(1);
        log.info("hqlSelectConditionalQuery ==> " + hBook.getName());
    }

    /**
     * 投影查询
     * // * 注意：HBook必须提供相应的构造方法。
     * // * 如果投影使用id，结果脱管态对象。
     */
    @Test
    public void hqlprojectionQuery() {
        List<HBook> list = service.hqlprojectionQuery();
        for (HBook hBook : list) {
            log.info("hqlprojectionQuery ==> " + hBook.getId());
        }
    }

    /**
     * 排序查询
     */
    @Test
    public void hqlSort() {
        List<HBook> list = service.hqlSort();
        for (HBook hBook : list) {
            log.info("hqlSelectAll ==> " + hBook.getName());
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void hqlPagin() {
        List<HBook> list = service.hqlPagin();
        for (HBook hBook : list) {
            log.info("hqlSelectAll ==> " + hBook.getName());
        }
    }

    /**
     * 聚合函数和分组
     */
    @Test
    public void hqlAggregate() {
        long num = service.hqlAggregate();
        log.info("hqlAggregate ==> " + num);
    }

    /**
     * 连接查询
     * <p>
     * //左外连接
     * //    List list = session.createQuery("from Customer c left outer join c.orderSet ").list();
     * //迫切左外链接 (默认数据重复)
     * //    List list = session.createQuery("from Customer c left outer join fetch
     * c.orderSet ").list();
     * //迫切左外链接 (去重复)
     * List list = session.createQuery("select distinct c from Customer c left outer join f
     * etch c.orderSet ").list();
     */
    @Test
    public void hqlJoinSelect() {
        List<Object[]> list = service.hqlJoinSelect();
        for (Object[] objects : list) {
            log.info("hqlJoinSelect ==> " + objects.length);
        }
    }

    /**
     *   HQL
     *   END =======================================
     */

    /**
     *   QBC
     */


    /**
     * Hibernate SQL
     */
    @Test
    public void sqlList() {
        List<HBook> list = service.listSQL();
        for (int i = 0; i < list.size(); i++) {
            log.info("hibernate SQL的使用   " + list.get(i).getName());
        }
    }

    /**
     * hibernate 的一级缓存
     * <p>
     * 当一个对象处于持久态时，修改其参数不需要update这个对象也可以
     * 修改数据库，当事务提交了之后就会对比快照里面的这个对象是否修改了数据，然后修改数据库
     */
    @Test
    public void firstLevelCache() {
        service.firstLevelCache(1);
    }

    /**
     * 类级别的延迟加载
     * load
     */
    @Test
    public void lazyLoad() {
        HBook hBook = service.lazyLoad(1);
    }

    /**
     * 关联级别的延迟加载（一对多：<set>）
     */
    @Test
    public void lazyMoreToMoreLoad() {
        HBook hBook = service.lazyMoreToMoreLoad(1);
    }

    /**
     * 抓取策略（使用下面两个策略，延迟加载就没用了）
     * 默认<set fetch="select">
     * <p>
     * 使用左外连接
     * （一对多：<set fetch="join">）
     * 使用子查询
     * （一对多：<set fetch="subselect">）
     * 先获取一方的数据，再通过一方数据的id把所有多方查询出来，当需要大量查询
     * 关联数据时使用，只会出现2个SQL语句
     */
    @Test
    public void test() {
        HBook hBook = service.lazyMoreToMoreLoad(1);
        Set<HAuthor> set = hBook.gethAuthors();
        for (HAuthor hAuthor : set) {
            log.info("关联级别的延迟加载 == " + hAuthor);
        }
    }

    /**
     * 使用Threadload管理session
     * <p>
     * 1) <!--        开启hibernate的threadlocal-->
     * <property name="current_session_context_class">thread</property>
     * <p>
     * 2) sessionFactory.getCurrentSession()
     * <p>
     * 在同一个进程将会使用同一个session
     */
    @Test
    public void hibernateSession() {
        service.hibernateSession(1);
    }

    /**
     * 最基础的hibernate使用
     * 搜索
     */
    @Test
    public void selectBook() {
        Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession(); // 相当于得到一个Connection
        // 根据业务来编写代码
        Query query = session.createQuery("FROM hbook"); // HQL语句，它类似于SQL语句
        List<HBook> list = query.list();

        session.close();
        sessionFactory.close();

        for (HBook book : list) {
            log.info("hibernate使用输出：" + book);
        }
    }

    /**
     * 最基础的hibernate使用
     * 插入
     */
    @Test
    public void insertBook() {
        //创建对象
        HBook book = new HBook();
//        book.setNumber("121");
        book.setName("测试");
        //获取加载配置管理类
        Configuration configuration = new Configuration();
        //不给参数就默认加载hibernate.cfg.xml文件，
        configuration.configure();
        //创建Session工厂对象
        SessionFactory factory = configuration.buildSessionFactory();
        //得到Session对象
        Session session = factory.openSession();
        //使用Hibernate操作数据库，都要开启事务,得到事务对象
        Transaction transaction = session.getTransaction();
        //开启事务
        transaction.begin();
        //把对象添加到数据库中
        session.save(book);
        //提交事务
        transaction.commit();
        //关闭Session
        session.close();
        factory.close();
    }


}
