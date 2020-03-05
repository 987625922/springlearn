package com.wind.spring.databases.hibernate.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class HBook implements Serializable {


    private static final long serialVersionUID = 8153103603243117845L;

    private Long id;

    private String name;

    private Set<HBookOrder> hBookOrders;

    public Set<HBookOrder> gethBookOrders() {
        return hBookOrders;
    }

    public void sethBookOrders(Set<HBookOrder> hBookOrders) {
        this.hBookOrders = hBookOrders;
    }
}