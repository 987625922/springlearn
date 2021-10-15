package com.learn.dubbo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * RPC接口DTO
 * 注意这里要实现序列化接口
 */
@Data
public class ProviderBean implements Serializable {
    private int id;
    private String name;
    private int number;
}
