package com.learn.security.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 权限实体
 */
@Data
public class SysMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private Long menuId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String permission;

}
