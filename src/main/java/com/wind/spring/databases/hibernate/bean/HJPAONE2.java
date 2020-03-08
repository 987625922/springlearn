package com.wind.spring.databases.hibernate.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="h_jpa_one2")
public class HJPAONE2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    /**
     * @JoinColumn 会在这张表市场一个名为hjpaone1_id的外键id
     */
    @OneToOne(targetEntity = HJPAONE2.class)
    @JoinColumn(name = "hjpaone1_id")
    private HJPAONE hjpaone;

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

    public HJPAONE getHjpaone() {
        return hjpaone;
    }

    public void setHjpaone(HJPAONE hjpaone) {
        this.hjpaone = hjpaone;
    }
}
