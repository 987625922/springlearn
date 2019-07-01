package com.wind.spring.mybatis;

import com.wind.spring.bean.Book;
import com.wind.spring.mybatis.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDaoService {

    @Autowired
    BookDao bookDao;

    public void testQueryAll() {
        List<Book> books = bookDao.queryAll(0, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }


}
