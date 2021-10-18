package com.learn.web;

import com.learn.web.bean.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping("/book1")
    public Object selectBook() {
        Book book = new Book();
        book.setId(1);
        book.setBookIndex("12");
        book.setName("百科全书");
        book.setNumber(12);
        return book;
    }
}
