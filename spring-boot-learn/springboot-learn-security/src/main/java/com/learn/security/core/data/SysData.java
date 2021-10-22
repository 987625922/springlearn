package com.learn.security.core.data;

import com.learn.security.core.entity.*;

import java.util.ArrayList;
import java.util.List;

public class SysData {

    /**
     * CREATE TABLE `sys_menu`  (
     * `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
     * `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
     * `permission` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
     * PRIMARY KEY (`menu_id`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_menu
     * -- ----------------------------
     * INSERT INTO `sys_menu` VALUES (1, '查看用户信息', 'sys:user:info');
     * INSERT INTO `sys_menu` VALUES (2, '查看所有权限', 'sys:menu:info');
     * INSERT INTO `sys_menu` VALUES (3, '查看所有角色', 'sys:role:info');
     *
     * @return
     */
    public static List<SysMenuEntity> getSysMenuList() {
        List<SysMenuEntity> list = new ArrayList<>();
        SysMenuEntity entity1 = new SysMenuEntity();
        entity1.setMenuId(1L);
        entity1.setName("查看用户列表");
        entity1.setPermission("sys:user:info");
        SysMenuEntity entity2 = new SysMenuEntity();
        entity2.setMenuId(2L);
        entity2.setName("查看角色列表");
        entity2.setPermission("sys:role:info");
        SysMenuEntity entity3 = new SysMenuEntity();
        entity3.setMenuId(3L);
        entity3.setName("查看权限列表");
        entity3.setPermission("sys:menu:info");
        list.add(entity1);
        list.add(entity2);
        list.add(entity3);
        return list;
    }

    /**
     * CREATE TABLE `sys_role`  (
     * `role_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
     * `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
     * PRIMARY KEY (`role_id`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_role
     * -- ----------------------------
     * INSERT INTO `sys_role` VALUES (1, 'ADMIN');
     * INSERT INTO `sys_role` VALUES (2, 'USER');
     *
     * @return
     */
    public static List<SysRoleEntity> getRoleList() {
        List<SysRoleEntity> list = new ArrayList<>();
        SysRoleEntity entity1 = new SysRoleEntity();
        entity1.setRoleId(1L);
        entity1.setRoleName("ADMIN");
        SysRoleEntity entity2 = new SysRoleEntity();
        entity2.setRoleId(2L);
        entity2.setRoleName("USER");
        list.add(entity1);
        list.add(entity2);
        return list;
    }

    /**
     * CREATE TABLE `sys_role_menu`  (
     * `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
     * `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色ID',
     * `menu_id` bigint(11) NULL DEFAULT NULL COMMENT '权限ID',
     * PRIMARY KEY (`id`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与权限关系表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_role_menu
     * -- ----------------------------
     * INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
     * INSERT INTO `sys_role_menu` VALUES (2, 1, 2);
     * INSERT INTO `sys_role_menu` VALUES (3, 1, 3);
     * INSERT INTO `sys_role_menu` VALUES (4, 2, 1);
     *
     * @return
     */
    public static List<SysRoleMenuEntity> getRoleMenuList() {
        List<SysRoleMenuEntity> list = new ArrayList<>();
        SysRoleMenuEntity entity1 = new SysRoleMenuEntity();
        entity1.setId(1L);
        entity1.setRoleId(1L);
        entity1.setMenuId(1L);
        SysRoleMenuEntity entity2 = new SysRoleMenuEntity();
        entity2.setId(2L);
        entity2.setRoleId(1L);
        entity2.setMenuId(2L);
        SysRoleMenuEntity entity3 = new SysRoleMenuEntity();
        entity3.setId(3L);
        entity3.setRoleId(1L);
        entity3.setMenuId(3L);
        SysRoleMenuEntity entity4 = new SysRoleMenuEntity();
        entity4.setId(4L);
        entity4.setRoleId(2L);
        entity4.setMenuId(1L);
        list.add(entity1);
        list.add(entity2);
        list.add(entity3);
        list.add(entity4);
        return list;
    }

    /**
     * CREATE TABLE `sys_user`  (
     * `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
     * `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
     * `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
     * `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 PROHIBIT：禁用   NORMAL：正常',
     * PRIMARY KEY (`user_id`) USING BTREE,
     * UNIQUE INDEX `username`(`username`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_user
     * -- ----------------------------
     * INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$5T851lZ7bc2U87zjt/9S6OkwmLW62tLeGLB2aCmq3XRZHA7OI7Dqa', 'NORMAL');
     * INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$szHoqQ64g66PymVJkip98.Fap21Csy8w.RD8v5Dhq08BMEZ9KaSmS', 'NORMAL');
     *
     * @return
     */
    public static List<SysUserEntity> getUserList() {
        List<SysUserEntity> list = new ArrayList<>();
        SysUserEntity entity1 = new SysUserEntity();
        entity1.setUserId(1L);
        entity1.setUsername("admin");
        entity1.setPassword("$2a$10$5T851lZ7bc2U87zjt/9S6OkwmLW62tLeGLB2aCmq3XRZHA7OI7Dqa");
        entity1.setStatus("NORMAL");
        SysUserEntity entity2 = new SysUserEntity();
        entity2.setUserId(2L);
        entity2.setUsername("user");
        entity2.setPassword("$2a$10$szHoqQ64g66PymVJkip98.Fap21Csy8w.RD8v5Dhq08BMEZ9KaSmS");
        entity2.setStatus("NORMAL");
        list.add(entity1);
        list.add(entity2);
        return list;
    }

    /**
     * CREATE TABLE `sys_user_role`  (
     * `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
     * `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户ID',
     * `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色ID',
     * PRIMARY KEY (`id`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色关系表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_user_role
     * -- ----------------------------
     * INSERT INTO `sys_user_role` VALUES (1, 1, 1);
     * INSERT INTO `sys_user_role` VALUES (2, 2, 2);
     *
     * @return
     */
    public static List<SysUserRoleEntity> getUserRoleList() {
        List<SysUserRoleEntity> list = new ArrayList<>();
        SysUserRoleEntity entity1 = new SysUserRoleEntity();
        entity1.setId(1L);
        entity1.setUserId(1L);
        entity1.setRoleId(1L);
        SysUserRoleEntity entity2 = new SysUserRoleEntity();
        entity2.setId(2L);
        entity2.setUserId(2L);
        entity2.setRoleId(2L);
        list.add(entity1);
        list.add(entity2);
        return list;
    }
}
