package org.learn.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LL
 * @Description:UserAgentUtils工具的基本api
 * @Date:Create：in 2020/7/16 13:27
 */
@Controller
public class UserAgentUtils {

    @GetMapping("/apis/getbrowser/info")
    public void getBrowserInfo(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        //解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();

        System.out.println("浏览器名:" + browser.getName());
        System.out.println("浏览器类型:" + browser.getBrowserType());
        System.out.println("浏览器家族:" + browser.getGroup());
        System.out.println("浏览器生产厂商:" + browser.getManufacturer());
        System.out.println("浏览器使用的渲染引擎:" + browser.getRenderingEngine());
        System.out.println("浏览器版本:" + userAgent.getBrowserVersion());

        System.out.println("操作系统名:" + operatingSystem.getName());
        System.out.println("访问设备类型:" + operatingSystem.getDeviceType());
        System.out.println("操作系统家族:" + operatingSystem.getGroup());
        System.out.println("操作系统生产厂商:" + operatingSystem.getManufacturer());
    }

}
