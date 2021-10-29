package com.learn.common.dubbo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DubboUserBean implements Serializable {
    private String id;
    private String username;
    private String address;
    private String phone;
}
