package com.wind.spring.springbean.autodiscovery;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

@Component
public class InjectUse {
    public MovieFinder movieFinder;
    //等效于@Autowired
    @Inject//如果想使用特定名称进行依赖注入，使用@Named，@Named与@Component等效
    public void setMovieFinder(@Named("jpaMovieFinder")MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
