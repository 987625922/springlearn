package org.learn.shiro.core.service;


import org.learn.shiro.core.entity.SysUserEntity;

/**
 * @Description 系统用户业务接口
 */
public interface SysUserService {

    /**
     * 根据用户名查询实体
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysUserEntity selectUserByName(String username);

}

