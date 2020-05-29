package org.learn.springcache;

import org.learn.common.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 *
 * @Cacheable 主要针对方法配置。能够依据方法的请求參数对其结果进行缓存
 * @CachePut 主要针对方法配置，能够依据方法的请求參数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
 * @CachEvict 主要针对方法配置。能够依据一定的条件对缓存进行清空
 *
 */
@Service
@Slf4j
public class CacheUseService {

    @Cacheable(value = "cacheUse")
    public Book getValueByName(String bookName) {
        log.info("===>>未使用缓存：" + bookName);
        Book book = new Book();
        book.setName(bookName);
        return book;
    }

    @CachePut(value = "cacheUse", key = "#book.getName()")
    public void updateAccount(Book book) {
        log.info("更新数据");
    }

    @CacheEvict(value = "cacheUse", allEntries = true)
    public void reload() {}

}
