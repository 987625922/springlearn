package com.wind.spring.spring.autodiscovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

//Qualifier 按类型自动装配可能有多个bean实例的情况，可以使用spring的@Qualifier注解缩小范围（或指定唯一），也可以用于指定单独的构造器参数或方法参数
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
}
