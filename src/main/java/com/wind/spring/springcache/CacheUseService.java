package com.wind.spring.springcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheUseService {

    @Cacheable("cacheUse")
    public String getValueByName(String name) {
        log.debug("===>>未使用缓存："+name);
        return name;
    }

}
