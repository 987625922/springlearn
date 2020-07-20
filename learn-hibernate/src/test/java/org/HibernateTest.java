package org;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.hibernate.bean.HBook;
import org.learn.hibernate.dao.HBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * hibernate的测试类
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-hibernate.xml"})
public class HibernateTest {

    /**
     * HBook实体类的service
     */
    @Autowired
    private HBookDao dao;

    /**
     * 保存课本
     */
    @Test
    public void save() {
        HBook hBook = new HBook();
        hBook.setName("测试书本");
        dao.save(hBook);
    }

    /**
     * 根据id获取课本
     */
    @Test
    public void getById() {
        log.info("=====>" + dao.get(1));
    }

    /**
     * jqpl 获取所有的课本
     */
    @Test
    public void selectAllList() {
        dao.selectAllList().forEach(System.out::println);
    }

    /**
     * 根据id获取课本
     */
    @Test
    public void selectById() {
        log.info("selectById()=====>" + dao.selectById(1L));
    }

    /**
     * 排序获取
     */
    @Test
    public void selectSort() {
        log.info("selectSort()=====>" + dao.selectSort());
    }

    /**
     * bewteen的使用
     */
    @Test
    public void selectBetweenAnd() {
        dao.selectBetweenAnd(1L, 10L)
                .forEach(System.out::println);
    }


    /**
     * hibernate sql的使用
     */
    @Test
    public void sqlTest() {
        dao.listSQL().forEach(hBook -> log.info("sqlTest()=====>" + hBook));
    }

    /**
     * 搜索没有作者的课本（size的使用）
     */
    @Test
    public void selectSize() {
        dao.selectSize().forEach(System.out::println);
    }

    /**
     * 隐式内连接的使用
     */
    @Test
    public void hideInnerJoin() {
        dao.hideInnerJoin().forEach(System.out::println);
    }

    /**
     * 内连接的使用
     */
    @Test
    public void innerJoin() {
        dao.innerJoin().forEach(System.out::println);
    }

    /**
     * hibernate聚合函数和分组的使用
     */
    @Test
    public void aggregateAndGroup() {
        dao.aggregateAndGroup().forEach(System.out::println);
    }

    /**
     * 子查询
     */
    @Test
    public void chilcSelect() {
        dao.chileSelect().forEach(System.out::println);
    }

    /**
     * hibernate分页查询
     */
    @Test
    public void hqlPagin() {
        dao.hqlPagin().forEach(System.out::println);
    }

    /**
     * 查总行数
     */
    @Test
    public void hqlAggregate() {
        log.info("hqlAggregate()======>" + dao.hqlAggregate());
    }

    /**
     * 最基础的hibernate使用
     * 插入
     */
    @Test
    public void insertBook() {
        //创建对象
        HBook book = new HBook();
        //book.setNumber("121");
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
}
