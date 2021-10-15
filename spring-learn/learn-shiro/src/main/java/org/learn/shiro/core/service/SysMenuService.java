package org.learn.shiro.core.service;


import org.learn.shiro.core.entity.SysMenuEntity;

import java.util.List;

/**
 * @Description 权限业务接口
 */
public interface SysMenuService {


    /**
     * 根据角色查询用户权限
     *
     * @Param roleId 角色ID
     * @Return List<SysMenuEntity> 权限集合
     */
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);

}

