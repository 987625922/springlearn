package org.learn.hibernate.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="h_jpa_more")
public class HJPAMORE1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    /**
     * jpa的一对多配置中的多方
     */
    @ManyToOne(targetEntity = HJPAONE.class)
    @JoinColumn(name = "hjpone_id")//表示这个表在维护外键
    private HJPAONE hjpaone;

    @ManyToMany(targetEntity = HJPAMORE2.class)
    //@JoinTable 用于映射中间表
    //joinColumns 当前方在中间表的外键字段名称
    //inverseJoinColumns 对方在中间表的外键字段名称
    @JoinTable(
            name = "h_more1_more2",
            joinColumns = @JoinColumn(name = "more1_id"),
            inverseJoinColumns = @JoinColumn(name = "more2_id")
    )
    private Set<HJPAMORE2> hjpamore2s = new HashSet<>();

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

    public Set<HJPAMORE2> getHjpamore2s() {
        return hjpamore2s;
    }

    public void setHjpamore2s(Set<HJPAMORE2> hjpamore2s) {
        this.hjpamore2s = hjpamore2s;
    }
}
