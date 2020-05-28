package com.wind.spring.databases.hibernate.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="h_jpa_one")
public class HJPAONE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;
    /**
     * jpa的一对多配置中的一方
     */
    //mappdBy 映射的是另一个表中要映射的参数名
    @OneToMany(targetEntity = HJPAMORE1.class,mappedBy = "hjpaone")
    private Set<HJPAMORE1> hjpamore1Set = new HashSet<>();

    @OneToOne(targetEntity = HJPAONE2.class,mappedBy = "hjpaone")
    private HJPAONE2 hjpaone2;

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

    public HJPAONE2 getHjpaone2() {
        return hjpaone2;
    }

    public void setHjpaone2(HJPAONE2 hjpaone2) {
        this.hjpaone2 = hjpaone2;
    }
}
