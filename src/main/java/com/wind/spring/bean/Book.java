package com.wind.spring.bean;

import lombok.Data;

@Data
public class Book {
    private String book_id;
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
