package com.wind.spring.other.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * api返回客户端格式
 */
@Data
public class JsonData implements Serializable {

    private int code;
    private Object data;
    private String msg;

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
