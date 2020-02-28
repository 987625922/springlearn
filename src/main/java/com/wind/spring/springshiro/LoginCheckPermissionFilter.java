package com.wind.spring.springshiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义登录验证过滤器
 *
 * 过滤器主要用于校验用户访问某个URL时，是否已经提前登录过，如果登录过，则允许访问，否则拒绝访问；
 * 此过滤器继承了AuthorizationFilter，并重写了isAccessAllowed方法和onAccessDenied方法
 */

@Slf4j
public class LoginCheckPermissionFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object arg2)
            throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        try {
            Subject subject = SecurityUtils.getSubject();

            return subject.isPermitted(url);
        } catch (Exception e) {
            log.error("Check perssion error", e);
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws IOException {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
            return true;
        }
        return false;
    }
}
