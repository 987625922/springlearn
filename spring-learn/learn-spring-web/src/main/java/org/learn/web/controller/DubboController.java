package org.learn.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.learn.dubbo.service.ProviderService;
import com.learn.dubbo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dubbo的使用controller
 */
@Slf4j
@RestController
public class DubboController {

    @Reference
    private ProviderService providerService;

    @RequestMapping(path = {"/dubbo/hello", "/"})
    public String hello() {
        return "hello world!";
    }


    /**
     * GET localhost:8080/dubbo/sendMessage?message=dubbo
     *
     * @param message
     * @return
     */
    @RequestMapping("/dubbo/sendMessage")
    public String getDubbo(String message) {
        log.info("getDubbo ==> " + message);
        return providerService.sayHello(message);
    }

    /**
     * GET localhost:8080/dubbo/pro
     * @return
     */
    @RequestMapping("/dubbo/pro")
    public Object getProvider() {
        log.info("getProvider ==> ");
        return new ResultVO.Builder<>().code(200).message("success")
                .data(providerService.getProviderBean()).build();
    }
}
