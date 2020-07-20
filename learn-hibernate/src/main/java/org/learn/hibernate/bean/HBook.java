package org.learn.hibernate.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * hiberante的mapper的实体类
 * 地址：
 * resource/hmapper/Book.hbm.xml
 *
 * @author LL
 */
@Setter
@Getter
public class HBook implements Serializable {

    private static final long serialVersionUID = 8153103603243117845L;

    private Long id;

    private String name;
    /**
     * 课本数量
     */
    private Long number;

    /**
     * 一对一
     */
    private HBookInfo hBookInfo;

    /**
     * 一对多
     */
    private Set<HBookOrder> hBookOrders = new HashSet<>();

    /**
     * 多对多
     */
    private Set<HAuthor> hAuthors = new HashSet<>();

    public HBook() {
    }

    /**
     * 用于hibernate的投影查询
     *
     * @param id
     */
    public HBook(Long id) {
        this.id = id;
    }

    public HBookInfo gethBookInfo() {
        return hBookInfo;
    }

    public void sethBookInfo(HBookInfo hBookInfo) {
        this.hBookInfo = hBookInfo;
    }

    public Set<HBookOrder> gethBookOrders() {
        return hBookOrders;
    }

    public void sethBookOrders(Set<HBookOrder> hBookOrders) {
        this.hBookOrders = hBookOrders;
    }

    public Set<HAuthor> gethAuthors() {
        return hAuthors;
    }

    public void sethAuthors(Set<HAuthor> hAuthors) {
        this.hAuthors = hAuthors;
    }

    @Override
    public String toString() {
        return "HBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}