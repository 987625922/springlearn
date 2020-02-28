package com.wind.spring.springshiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm
 *
 * 继承AuthorizingRealm，
 * 并实现doGetAuthorizationInfo权限验证方法和doGetAuthenticationInfo身份验证方法
 */
public class UserShiroRealm extends AuthorizingRealm {

    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Shiro进行身份验证时，会调用到doGetAuthenticationInfo方法，
     * 在方法内部，我们通过UsernamePasswordToken 获得用户传过来的用户名，
     * 再通过userService.selectUserByUserName方法从数据库中查询用户信息
     * ，如果用户为空，说账号不存在，否则将查询出来的用户名及密码，封装到SimpleAuthenticationInfo 对象中，
     * 并返回，用于接下来的密码验证
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        if(username == null) {
//            throw new BugException("未登录");
            throw new ArrayIndexOutOfBoundsException();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        Set<String> stringPermissions = new HashSet<String>();
        roles.add("USER");
        stringPermissions.add("USER:DELETE");//角色:权限

        info.setRoles(roles);//角色可以通过数据库查询得到
        info.setStringPermissions(stringPermissions);//权限可以通过数据库查询得到

        return info;
    }

    /**
     * Shiro角色权限验证，会调用doGetAuthorizationInfo方法，
     * 通过SimpleAuthorizationInfo.setRoles()方法设置用户角色，
     * 通过SimpleAuthorizationInfo.setStringPermissions()设置用户权限，
     * 这里暂时给个空集合，在项目中，用户的角色权限需要从数据库中查询
     * @param autToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken autToken) throws AuthenticationException {

        UsernamePasswordToken userPwdToken = (UsernamePasswordToken) autToken;
        String userName = userPwdToken.getUsername();

        UserVO user = userService.selectUserByUserName(userName);
        if (null == user) {
//            throw new BugException("未知账号");
            throw new ArrayIndexOutOfBoundsException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),
                user.getPassword().toCharArray(), getName());

        return authenticationInfo;
    }
}
