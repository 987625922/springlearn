package com.learn.service;

import com.learn.dao.bean.Book;
import com.learn.dao.IBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    IBookMapper bookMapper;

    public List<Book> selectList() {
        return bookMapper.selectList();
    }

    public Book selectById(Integer id) {
        return bookMapper.selectById(id);
    }

    public void insert(Book book) {
        bookMapper.insert(book);
    }

    public void update(Book book) {
        bookMapper.update(book);
    }

    public void delete(Integer id, String name) {
        bookMapper.delete(id, name);
    }

    public void deleteById(Integer id){
        bookMapper.deleteById(id);
    }
}
