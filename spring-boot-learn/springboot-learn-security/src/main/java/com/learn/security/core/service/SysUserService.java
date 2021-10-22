package com.learn.security.core.service;


import com.learn.security.core.entity.SysMenuEntity;
import com.learn.security.core.entity.SysRoleEntity;
import com.learn.security.core.entity.SysUserEntity;

import java.util.List;

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

    List<SysMenuEntity> selectSysMenuByUserId(Long userId);

    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    List<SysUserEntity> list();
}

