package com.wind.spring.springshiro.project.service.impl;

import com.wind.spring.springshiro.project.dao.RoleMapper;
import com.wind.spring.springshiro.project.dao.UserMapper;
import com.wind.spring.springshiro.project.domain.Role;
import com.wind.spring.springshiro.project.domain.User;
import com.wind.spring.springshiro.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findAllUserInfoByUsername(String username) {
        User user = userMapper.findByUsername(username);

        //用户的角色集合
        List<Role> roleList = roleMapper.findRoleListByUserId(user.getId());


        user.setRoleList(roleList);

        return user;
    }


    @Override
    public User findSimpleUserInfoById(int userId) {
        return userMapper.findById(userId);
    }


    @Override
    public User findSimpleUserInfoByUsername(String username) {
        return userMapper.findByUsername(username);
    }


}
