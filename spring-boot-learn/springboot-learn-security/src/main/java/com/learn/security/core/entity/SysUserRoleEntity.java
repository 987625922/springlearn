package com.learn.security.core.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @Description 用户与角色关系实体
 */
@Data
public class SysUserRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
