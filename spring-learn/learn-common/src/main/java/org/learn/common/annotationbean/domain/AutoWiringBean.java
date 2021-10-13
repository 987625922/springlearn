package org.learn.common.annotationbean.domain;

import lombok.ToString;
import org.learn.common.bean.Book;

@ToString
public class AutoWiringBean {
    private Book book;
    private Book book1;

    public AutoWiringBean(Book book) {
        this.book = book;
    }

    public void setBook1(Book book1) {
        this.book1 = book1;
    }
}
