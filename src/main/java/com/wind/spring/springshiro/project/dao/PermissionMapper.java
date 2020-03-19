package com.wind.spring.springshiro.project.dao;

import com.wind.spring.springshiro.project.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {


    @Select("select p.id as id, p.name as name, p.url as url from  s_role_permission rp " +
            "left join s_permission p on rp.permission_id=p.id " +
            "where  rp.role_id= #{roleId} ")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);

}