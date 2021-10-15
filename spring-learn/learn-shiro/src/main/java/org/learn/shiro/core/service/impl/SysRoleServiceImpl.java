package org.learn.shiro.core.service.impl;

import org.learn.shiro.core.data.SysData;
import org.learn.shiro.core.entity.SysRoleEntity;
import org.learn.shiro.core.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色业务实现
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    /**
     * 通过用户ID查询角色集合
     *
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return SysData.getRoleList();
    }
}