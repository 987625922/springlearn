package com.wind.spring.databases.hibernate.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="h_jpa_more2")
public class HJPAMORE2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToMany(targetEntity = HJPAMORE1.class,mappedBy = "hjpamore2s")
    private Set<HJPAMORE1> hjpamore1Set = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HJPAMORE1> getHjpamore1Set() {
        return hjpamore1Set;
    }

    public void setHjpamore1Set(Set<HJPAMORE1> hjpamore1Set) {
        this.hjpamore1Set = hjpamore1Set;
    }
}
