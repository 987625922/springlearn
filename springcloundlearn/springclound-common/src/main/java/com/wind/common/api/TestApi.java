package com.wind.common.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 设置为controller，被field发现
 */
@FeignClient(value = "learn-service")
public interface TestApi {

    @PostMapping("/test/string")
    String getTestString();
}
