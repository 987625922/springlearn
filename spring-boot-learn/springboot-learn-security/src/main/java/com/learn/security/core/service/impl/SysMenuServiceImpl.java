package com.learn.security.core.service.impl;


import com.learn.security.core.data.SysData;
import com.learn.security.core.entity.SysMenuEntity;
import com.learn.security.core.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 权限业务实现
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {


    /**
     * 根据角色查询用户权限
     *
     * @Param roleId 角色ID
     * @Return List<SysMenuEntity> 权限集合
     */
    @Override
    public List<SysMenuEntity> selectSysMenuByRoleId(Long roleId) {
        return SysData.getSysMenuList();
    }

    @Override
    public List<SysMenuEntity> list() {
        return SysData.getSysMenuList();
    }
}