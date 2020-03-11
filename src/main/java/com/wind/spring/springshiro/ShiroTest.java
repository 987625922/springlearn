package com.wind.spring.springshiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/application-shiro.xml"})
public class ShiroTest {

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    /**
     * shiro授权
     */
    @Before
    public void init() {
        simpleAccountRealm.addAccount("cs", "123");
        simpleAccountRealm.addAccount("cs1", "456");
        defaultSecurityManager.setRealm(simpleAccountRealm);
    }

    /**
     * shiro认证
     */
    @Test
    public void test() {
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //操作主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("cs", "123");
        subject.login(token);

        log.debug("认证结果：" + subject.isAuthenticated());
        log.debug("getPrincipal："+subject.getPrincipal());
    }
}
