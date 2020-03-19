package com.wind.spring.springshiro.project.dao;

import com.wind.spring.springshiro.project.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {

    @Select("select * from s_user where username = #{username}")
    User findByUsername(@Param("username") String username);


    @Select("select * from s_user where id=#{userId}")
    User findById(@Param("userId") int id);


    @Select("select * from s_user where username = #{username} and password = #{pwd}")
    User findByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);


}