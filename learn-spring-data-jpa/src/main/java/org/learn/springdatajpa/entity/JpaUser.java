package org.learn.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "j_jpa_user")
public class JpaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "sex")
    private Integer sex;//0未知1男2女
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

}
