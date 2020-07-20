package org.learn.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Book implements Serializable {

    private int id;
    private String name;
    private String number;

    /*
     * 初始化方法
     * */
    public void init() {
        System.out.println("初始化成功");
    }

    /*
     * 销毁方法
     * */
    public void cleanup() {

    }

}
