package com.wind.spring.databases.hibernate.bean;

import java.io.Serializable;

public class HBookInfo implements Serializable {

    private static final long serialVersionUID = -8797330300269686280L;
    private long id;

    private String name;

    private HBook hBook;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HBook gethBook() {
        return hBook;
    }

    public void sethBook(HBook hBook) {
        this.hBook = hBook;
    }
}
