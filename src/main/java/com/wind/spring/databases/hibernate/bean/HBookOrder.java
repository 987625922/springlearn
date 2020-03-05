package com.wind.spring.databases.hibernate.bean;

import lombok.Data;

import java.io.Serializable;

@Data
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
}
