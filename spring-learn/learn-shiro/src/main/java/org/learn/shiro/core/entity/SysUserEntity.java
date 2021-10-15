package org.learn.shiro.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 系统用户实体
 */
@Data
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String state;

}
