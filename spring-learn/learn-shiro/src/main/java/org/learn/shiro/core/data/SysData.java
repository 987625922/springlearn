package org.learn.shiro.core.data;

import org.learn.shiro.core.entity.*;

import java.util.ArrayList;
import java.util.List;

public class SysData {

    /**
     * CREATE TABLE `sys_menu`  (
     * `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
     * `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
     * `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
     * PRIMARY KEY (`menu_id`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_menu
     * -- ----------------------------
     * INSERT INTO `sys_menu` VALUES (1, '查看用户列表', 'sys:user:info');
     * INSERT INTO `sys_menu` VALUES (2, '查看角色列表', 'sys:role:info');
     * INSERT INTO `sys_menu` VALUES (3, '查看权限列表', 'sys:menu:info');
     * INSERT INTO `sys_menu` VALUES (4, '查看所有数据', 'sys:info:all');
     *
     * @return
     */
    public static List<SysMenuEntity> getSysMenuList() {
        List<SysMenuEntity> list = new ArrayList<>();
        SysMenuEntity entity1 = new SysMenuEntity();
        entity1.setMenuId(1L);
        entity1.setName("查看用户列表");
        entity1.setPerms("sys:user:info");
        SysMenuEntity entity2 = new SysMenuEntity();
        entity1.setMenuId(2L);
        entity1.setName("查看角色列表");
        entity1.setPerms("sys:role:info");
        SysMenuEntity entity3 = new SysMenuEntity();
        entity1.setMenuId(3L);
        entity1.setName("查看权限列表");
        entity1.setPerms("sys:menu:info");
        SysMenuEntity entity4 = new SysMenuEntity();
        entity1.setMenuId(4L);
        entity1.setName("查看所有数据");
        entity1.setPerms("sys:info:all");
        list.add(entity1);
        list.add(entity2);
        list.add(entity3);
        list.add(entity4);
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
        entity1.setId(2L);
        entity1.setRoleId(1L);
        entity1.setMenuId(2L);
        SysRoleMenuEntity entity3 = new SysRoleMenuEntity();
        entity1.setId(3L);
        entity1.setRoleId(1L);
        entity1.setMenuId(3L);
        SysRoleMenuEntity entity4 = new SysRoleMenuEntity();
        entity1.setId(4L);
        entity1.setRoleId(2L);
        entity1.setMenuId(1L);
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
     * `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
     * `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态:NORMAL正常  PROHIBIT禁用',
     * PRIMARY KEY (`user_id`) USING BTREE
     * ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;
     * <p>
     * -- ----------------------------
     * -- Records of sys_user
     * -- ----------------------------
     * INSERT INTO `sys_user` VALUES (1, 'admin', 'a1bb09ad5dea12e0f94762cb116c447e80c784d8aa2c6625263f7f3436cdd583', 'RvP3UID2n30Q2sycZYvH', 'NORMAL');
     * INSERT INTO `sys_user` VALUES (2, 'user', '376eb5d2698c804ee83594fe8b0217f03ad138a046f7fa42b44c232c2e5e2b38', 'OVlrD37bDUKNcFRB10qG', 'NORMAL');
     *
     * @return
     */
    public static List<SysUserEntity> getUserList() {
        List<SysUserEntity> list = new ArrayList<>();
        SysUserEntity entity1 = new SysUserEntity();
        entity1.setUserId(1L);
        entity1.setUsername("admin");
        entity1.setPassword("a1bb09ad5dea12e0f94762cb116c447e80c784d8aa2c6625263f7f3436cdd583");
        entity1.setSalt("RvP3UID2n30Q2sycZYvH");
        entity1.setState("NORMAL");
        SysUserEntity entity2 = new SysUserEntity();
        entity2.setUserId(2L);
        entity2.setUsername("user");
        entity2.setPassword("376eb5d2698c804ee83594fe8b0217f03ad138a046f7fa42b44c232c2e5e2b38");
        entity2.setSalt("OVlrD37bDUKNcFRB10qG");
        entity2.setState("NORMAL");
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
