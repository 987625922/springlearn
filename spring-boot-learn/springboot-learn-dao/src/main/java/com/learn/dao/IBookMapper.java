package com.learn.dao;

import com.learn.bean.Book;

import java.util.List;

public interface IBookMapper {

    List<Book> selectList();

    Book selectById(Integer id);

    void insert(Book book);

    void update(Book book);

    void delete(Integer id, String name);

    void deleteById(Integer id);
}
