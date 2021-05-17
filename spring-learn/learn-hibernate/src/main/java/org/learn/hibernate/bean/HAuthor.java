package org.learn.hibernate.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class HAuthor implements Serializable {

    private static final long serialVersionUID = 5516215546168184576L;

    private long id;

    private String name;

    private Set<HBook> hBooks = new HashSet<>();

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

    public Set<HBook> gethBooks() {
        return hBooks;
    }

    public void sethBooks(Set<HBook> hBooks) {
        this.hBooks = hBooks;
    }
}
