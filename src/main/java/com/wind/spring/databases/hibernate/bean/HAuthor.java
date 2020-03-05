package com.wind.spring.databases.hibernate.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class HAuthor implements Serializable {

    private static final long serialVersionUID = -8797330300269686280L;
    private long id;

    private String name;

    private Set<HBook> hBooks = new HashSet<>();

    public Set<HBook> gethBooks() {
        return hBooks;
    }

    public void sethBooks(Set<HBook> hBooks) {
        this.hBooks = hBooks;
    }
}
