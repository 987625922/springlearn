package com.wind.spring.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * hibernate使用的bean
 */
@Getter
@Setter
@ToString
public class HBook {
    private int book_id;
    private String name;
    private String number;

}
