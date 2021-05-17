package org.learn.springcache;

import lombok.extern.slf4j.Slf4j;
import org.learn.common.bean.Book;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;


/**
 * @Cacheable 主要针对方法配置。能够依据方法的请求參数对其结果进行缓存
 * @CachePut 主要针对方法配置，能够依据方法的请求參数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
 * @CachEvict 主要针对方法配置。能够依据一定的条件对缓存进行清空
 */
@Service
@Slf4j
public class CacheUseService {
    /**
     * String[] value();             //请参考@CachePut
     * String key() default "";      //请参考@CachePut
     * String condition() default "";//请参考@CachePut
     * String unless() default "";   //请参考@CachePut
     */
    @Cacheable(value = "cacheUse")
    public Book getValueByName(String bookName) {
        log.info("===>>未使用缓存：" + bookName);
        Book book = new Book();
        book.setName(bookName);
        return book;
    }

    /**
     * String[] value();              //缓存的名字，可以把数据写到多个缓存
     * String key() default "";       //缓存key，如果不指定将使用默认的KeyGenerator生成
     * String condition() default ""; //满足缓存条件的数据才会放入缓存，condition在调用方法之前和之后都会判断
     * String unless() default "";    //用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了
     */
    @CachePut(value = "cacheUse", key = "#book.getName()")
    public void updateAccount(Book book) {
        log.info("更新数据");
    }

    /**
     * String[] value();                        //请参考@CachePut
     * String key() default "";                 //请参考@CachePut
     * String condition() default "";           //请参考@CachePut
     * boolean allEntries() default false;      //是否移除所有数据
     * boolean beforeInvocation() default false;//是调用方法之前移除/还是调用之后移除
     */
    @CacheEvict(value = "cacheUse", allEntries = true)
    public void reload() {
        log.info("清除缓存");
    }

    /**
     * 有时候我们可能组合多个Cache注解使用；比如用户新增成功后，我们要添加id-->user；
     * username--->user；email--->user的缓存；此时就需要@Caching组合多个注解标签了。
     * 如用户新增成功后，添加id-->user；username--->user；email--->user到缓存；
     */
    @Caching(
            put = {
                    @CachePut(value = "user", key = "#user.id"),
                    @CachePut(value = "user", key = "#user.username"),
                    @CachePut(value = "user", key = "#user.email")
            }
    )
    public Book save(Book book) {
        return null;
    }
}
