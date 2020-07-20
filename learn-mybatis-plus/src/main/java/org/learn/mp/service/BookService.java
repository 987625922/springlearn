package org.learn.mp.service;

import org.learn.common.bean.Book;
import org.learn.mp.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LL
 * @Description: book实体类的service
 * @Date:Create：in 2020/7/20 14:16
 */
@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    public List<Book> getAllList() {
        return bookMapper.selectList(null);
    }
}
