package com.wind.spring.databases.hibernate.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class HBook implements Serializable {


    private static final long serialVersionUID = 8153103603243117845L;

    private Long id;

    private String name;

    private HBookInfo hBookInfo;

    private Set<HBookOrder> hBookOrders = new HashSet<>();

    private Set<HAuthor> hAuthors = new HashSet<>();

    public HBookInfo gethBookInfo() {
        return hBookInfo;
    }

    public void sethAuthor(HBookInfo hBookInfo) {
        this.hBookInfo = hBookInfo;
    }

    public Set<HBookOrder> gethBookOrders() {
        return hBookOrders;
    }

    public void sethBookOrders(Set<HBookOrder> hBookOrders) {
        this.hBookOrders = hBookOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HAuthor> gethAuthors() {
        return hAuthors;
    }

    public void sethAuthors(Set<HAuthor> hAuthors) {
        this.hAuthors = hAuthors;
    }
}