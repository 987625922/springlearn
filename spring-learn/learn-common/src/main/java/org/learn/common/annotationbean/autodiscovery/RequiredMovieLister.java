package org.learn.common.annotationbean.autodiscovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//Qualifier 按类型自动装配可能有多个bean实例的情况，
// 可以使用spring的@Qualifier注解缩小范围（或指定唯一），
// 也可以用于指定单独的构造器参数或方法参数
@Slf4j
@Component
public class RequiredMovieLister {

    private MovieFinder movieFinder;
    @Autowired
    @Qualifier("jpaMovieFinder")
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }

    //bean初始化时回调
    @PostConstruct
    public void populateMovieCache() {
        log.info("ResourceUse的bean初始化");
    }

    //bean销毁时回调
    @PreDestroy
    public void cleaMoviceCache() {
        log.info("ResourceUse的bean被销毁");
    }
}
