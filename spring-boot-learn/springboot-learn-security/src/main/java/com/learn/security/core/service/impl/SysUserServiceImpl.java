package com.learn.security.core.service.impl;

import com.learn.security.core.data.SysData;
import com.learn.security.core.entity.*;
import com.learn.security.core.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description 系统用户业务实现
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    /**
     * 根据用户名查询实体
     *
     * @Param username 用户名
     * @Return SysUserEntity 用户实体
     */
    @Override
    public SysUserEntity selectUserByName(String username) {
        List<SysUserEntity> users = SysData.getUserList();
        SysUserEntity entity = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                entity = users.get(i);
            }
        }
        return entity;
    }

    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        List<SysMenuEntity> result = new ArrayList<>();
        List<SysMenuEntity> menuEntities = SysData.getSysMenuList();
        List<SysUserRoleEntity> userRoleEntities = SysData.getUserRoleList();
        List<SysRoleMenuEntity> roleMenuEntities = SysData.getRoleMenuList();
        SysUserRoleEntity userRoleEntity = null;
        List<SysRoleMenuEntity> userRoleMenuEntities = new ArrayList<>();
        for (int i = 0; i < userRoleEntities.size(); i++) {
            if (userRoleEntities.get(i).getUserId() == userId) {
                userRoleEntity = userRoleEntities.get(i);
            }
        }
        for (int i = 0; i < roleMenuEntities.size(); i++) {
            if (roleMenuEntities.get(i).getRoleId() == userRoleEntity.getRoleId()) {
                userRoleMenuEntities.add(roleMenuEntities.get(i));
            }
        }
        for (int i = 0; i < menuEntities.size(); i++) {
            for (int j = 0; j < userRoleMenuEntities.size(); j++) {
                if (menuEntities.get(i).getMenuId() == userRoleMenuEntities.get(j).getMenuId()) {
                    result.add(menuEntities.get(i));
                }
            }
        }
        return result;
    }

    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        List<SysRoleEntity> resule = new ArrayList<>();
        AtomicReference<SysUserRoleEntity> userRoleEntity = new AtomicReference<>();
        SysData.getUserRoleList().forEach(bean -> {
            if (bean.getUserId() == userId) {
                userRoleEntity.set(bean);
            }
        });
        SysData.getRoleList().forEach(bean -> {
            if (bean.getRoleId() == userRoleEntity.get().getRoleId()) {
                resule.add(bean);
            }
        });
        return resule;
    }

    @Override
    public List<SysUserEntity> list() {
        return SysData.getUserList();
    }
}