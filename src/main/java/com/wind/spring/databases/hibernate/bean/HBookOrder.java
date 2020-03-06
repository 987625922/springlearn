package com.wind.spring.databases.hibernate.bean;

import java.io.Serializable;

public class HBookOrder implements Serializable {

    private static final long serialVersionUID = 1820858305983746211L;

    private long id;

    private float price;

    private HBook hBook;

    public HBook gethBook() {
        return hBook;
    }

    public void sethBook(HBook hBook) {
        this.hBook = hBook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
