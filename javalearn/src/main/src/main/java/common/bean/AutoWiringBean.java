package common.bean;

import lombok.ToString;

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
