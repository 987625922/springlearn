package org.learn.shiro.core.service.impl;

import org.learn.shiro.core.data.SysData;
import org.learn.shiro.core.entity.SysUserEntity;
import org.learn.shiro.core.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}