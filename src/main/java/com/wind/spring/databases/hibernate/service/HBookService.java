package com.wind.spring.databases.hibernate.service;

import com.wind.spring.databases.hibernate.bean.HAuthor;
import com.wind.spring.databases.hibernate.bean.HBookInfo;
import com.wind.spring.databases.hibernate.bean.HBook;
import com.wind.spring.databases.hibernate.bean.HBookOrder;
import com.wind.spring.databases.hibernate.dao.HAuthorDao;
import com.wind.spring.databases.hibernate.dao.HBookInfoDao;
import com.wind.spring.databases.hibernate.dao.HBookDao;
import com.wind.spring.databases.hibernate.dao.HBookOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        hBookDao.save(book);
        int i = 1/0;
        HBook book1 = new HBook();
        book1.setName("测试事务");
        hBookDao.save(book1);
    }

    public void saveBookAndOrderOneToMore(){
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

    public void saveBookAndInfoOneToOne(){
        HBookInfo hBookInfo = new HBookInfo();
        hBookInfo.setName("一对一关系映射");

        HBook hBook = new HBook();
        hBook.setName("一对一关系映射");

        hBookInfo.sethBook(hBook);
        hBook.sethAuthor(hBookInfo);

        hBookDao.save(hBook);
        authorDao.save(hBookInfo);
    }

    public void saveBookAndAuthorMoreToMore(){
//        HBook hBook = new HBook();
//        hBook.setName("多对多映射1");
//
//        HBook hBook1 = new HBook();
//        hBook1.setName("多对多映射2");
//
//        HAuthor hAuthor = new HAuthor();
//        hAuthor.setName("多对多映射1");
//
//        HAuthor hAuthor1 = new HAuthor();
//        hAuthor1.setName("多对多映射2");
//
//        hBook.gethAuthors().add(hAuthor);
//        hBook.gethAuthors().add(hAuthor1);
//        hBook1.gethAuthors().add(hAuthor);
//        hBook1.gethAuthors().add(hAuthor1);
//
//        hAuthor.gethBooks().add(hBook);
//        hAuthor.gethBooks().add(hBook1);
//        hAuthor1.gethBooks().add(hBook);
//        hAuthor1.gethBooks().add(hBook1);
//
//        hBookDao.save(hBook);
//        hBookDao.save(hBook1);
//        hAuthorDao.save(hAuthor);
//        hAuthorDao.save(hAuthor1);
    }

    public List<HBook> list() {
        return hBookDao.list();
    }
}
