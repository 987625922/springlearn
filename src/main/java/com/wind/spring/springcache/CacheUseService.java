package com.wind.spring.springcache;

import com.wind.spring.other.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheUseService {

    @Cacheable(value = "cacheUse")
    public Book getValueByName(String bookName) {
        log.debug("===>>未使用缓存：" + bookName);
        Book book = new Book();
        book.setName(bookName);
        return book;
    }

    @CacheEvict(value = "cacheUse", key = "#account.getName()")
    public void updateAccount(String name) {
        log.debug("更新数据");
    }

    @CacheEvict(value = "cacheUse", allEntries = true)
    public void reload() {
    }


}
