package org;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.shiro.config.CustomRealm;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * shiro简单的使用
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-web-shiro-old.xml"})
public class ShiroTest {
    /**
     * shiroTest最简单的使用
     */
    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    /**
     * 简单的shiro授权
     */
    @Before
    public void init() {
        simpleAccountRealm.addAccount("cs", "123");
        simpleAccountRealm.addAccount("cs1", "456");
        defaultSecurityManager.setRealm(simpleAccountRealm);
    }

    /**
     * 简单的shiro认证
     */
    @Test
    public void test() {
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //操作主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("cs", "123");
        subject.login(token);
        log.debug("认证结果：" + subject.isAuthenticated());
        log.debug("getPrincipal：" + subject.getPrincipal());
    }


    /**
     * =========== 自定义的Realm ========================
     */
    private CustomRealm enceladusShiroRealm = new CustomRealm();
    private DefaultSecurityManager enceladusDefaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init1() {
        enceladusDefaultSecurityManager.setRealm(enceladusShiroRealm);
        SecurityUtils.setSecurityManager(enceladusDefaultSecurityManager);
    }

    @Test
    public void testEncelAuthentication() {
        //获取当前操作的主体
        Subject subject = SecurityUtils.getSubject();
        //用户输入的账号密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("jack", "123");
        subject.login(usernamePasswordToken);
        //登录
        System.out.println(" 认证结果:" + subject.isAuthenticated());
        //拿到主体标示属性
        System.out.println(" getPrincipal=" + subject.getPrincipal());
        subject.checkRole("role1");
        System.out.println("是否有对应的角色:" + subject.hasRole("role1"));
        System.out.println("是否有对应的权限:" + subject.isPermitted("video:add"));
    }

}
