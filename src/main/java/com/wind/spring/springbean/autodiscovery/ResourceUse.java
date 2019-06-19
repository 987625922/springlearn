package com.wind.spring.springbean.autodiscovery;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class ResourceUse {
    public MovieFinder movieFinder;

    //等效于@Autowired
    @Resource(name = "jpaMovieFinder")
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    //bean初始化时回调
    @PostConstruct
    public void populateMovieCache() {
        System.out.println("ResourceUse的bean初始化");
    }

    //bean销毁时回调
    @PreDestroy
    public void cleaMoviceCache() {
        System.out.println("ResourceUse的bean被销毁");
    }
}
