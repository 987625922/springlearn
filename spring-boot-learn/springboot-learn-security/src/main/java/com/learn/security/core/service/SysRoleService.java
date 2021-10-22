package com.learn.security.core.service;



import com.learn.security.core.entity.SysRoleEntity;

import java.util.List;

/**
 * @Description 角色业务接口
 */
public interface SysRoleService  {

    /**
     * 通过用户ID查询角色集合
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    List<SysRoleEntity> list();
}

