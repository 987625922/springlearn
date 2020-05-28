package org.learn.hibernate.service;

import lombok.extern.slf4j.Slf4j;
import org.learn.hibernate.bean.HAuthor;
import org.learn.hibernate.bean.HBook;
import org.learn.hibernate.bean.HBookInfo;
import org.learn.hibernate.bean.HBookOrder;
import org.learn.hibernate.dao.HAuthorDao;
import org.learn.hibernate.dao.HBookDao;
import org.learn.hibernate.dao.HBookInfoDao;
import org.learn.hibernate.dao.HBookOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
@Service
public class HBookService {

    @Autowired
    private HBookDao hBookDao;

    @Autowired
    private HBookOrderDao hBookOrderDao;

    @Autowired
    private HBookInfoDao authorDao;

    @Autowired
    private HAuthorDao hAuthorDao;

    public void save(HBook book) {
        hBookDao.save(book);
    }

    public void saveTransaction(HBook book) {
        hBookDao.saveTransaction(book);
        int i = 1 / 0;
        HBook book1 = new HBook();
        book1.setName("测试事务");
        hBookDao.saveTransaction(book1);
    }

    public void saveBookAndOrderOneToMore() {
        HBook book = new HBook();
        book.setName("测试一对多关系映射");

        HBookOrder hBookOrder = new HBookOrder();
        hBookOrder.setPrice(1);
        HBookOrder hBookOrder1 = new HBookOrder();
        hBookOrder1.setPrice(2);

        hBookOrder.sethBook(book);
        hBookOrder1.sethBook(book);

//        book.gethBookOrders().add(hBookOrder);
//        book.gethBookOrders().add(hBookOrder1);

        hBookOrderDao.save(hBookOrder);
        hBookOrderDao.save(hBookOrder1);
        hBookDao.save(book);

    }

    public void saveBookAndInfoOneToOne() {
        HBookInfo hBookInfo = new HBookInfo();
        hBookInfo.setName("一对一关系映射");

        HBook hBook = new HBook();
        hBook.setName("一对一关系映射");

        hBookInfo.sethBook(hBook);
        hBook.sethBookInfo(hBookInfo);

        hBookDao.save(hBook);
        authorDao.save(hBookInfo);
    }


    public void saveBookAndAuthorMoreToMore() {
        HBook hBook = new HBook();
        hBook.setName("多对多映射1");

        HBook hBook1 = new HBook();
        hBook1.setName("多对多映射2");

        HAuthor hAuthor = new HAuthor();
        hAuthor.setName("多对多映射1");

        HAuthor hAuthor1 = new HAuthor();
        hAuthor1.setName("多对多映射2");

        hBook.gethAuthors().add(hAuthor);
        hBook.gethAuthors().add(hAuthor1);
        hBook1.gethAuthors().add(hAuthor);
        hBook1.gethAuthors().add(hAuthor1);

        hAuthor.gethBooks().add(hBook);
        hAuthor.gethBooks().add(hBook1);
        hAuthor1.gethBooks().add(hBook);
        hAuthor1.gethBooks().add(hBook1);
//
//        hBookDao.save(hBook);
//        hBookDao.save(hBook1);
        hAuthorDao.save(hAuthor);
        hAuthorDao.save(hAuthor1);
    }

    public void selectBookAndAuthorMoreToMore() {

    }

    public List<HBook> list() {
        return hBookDao.list();
    }


    public HBook getAndLoad(long id) {
        return hBookDao.getAndLoad(id);
    }

    public List<HBook> listSQL() {
        return hBookDao.listSQL();
    }

    public HBook firstLevelCache(long id) {
        HBook hBook = hBookDao.firstLevelCache(id);
        hBook.setName("hibernate的一级缓存");
        return hBook;
    }

    public HBook lazyLoad(long id) {
        HBook hBook = hBookDao.lazyLoad(id);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(" ==> " + hBook.getName());
        return hBook;
    }

    public HBook lazyMoreToMoreLoad(long id) {
        HBook hBook = hBookDao.lazyMoreToMoreLoad(id);
        Set<HAuthor> set = hBook.gethAuthors();
        for (HAuthor hAuthor : set) {
            log.debug("关联级别的延迟加载 == " + hAuthor);
        }
        return hBook;
    }

    public void hibernateSession(long id) {
        for (int i = 0; i < 30; i++) {
            hBookDao.get(id);
            log.debug("====");
        }
    }

    public List<HBook> hqlSelectAll() {
        return hBookDao.hqlSelectAll();
    }

    public HBook hqlSelectConditionalQuery(long id) {
        return hBookDao.hqlSelectConditionalQuery(id);
    }

    public List<HBook> hqlprojectionQuery() {
        return hBookDao.hqlprojectionQuery();
    }

    public List<HBook> hqlSort() {
        return hBookDao.hqlSort();
    }

    public List<HBook> hqlPagin() {
        return hBookDao.hqlPagin();
    }

    public Long hqlAggregate() {
        return hBookDao.hqlAggregate();
    }

    public List<Object[]> hqlJoinSelect() {
        return hBookDao.hqlJoinSelect();
    }
}
