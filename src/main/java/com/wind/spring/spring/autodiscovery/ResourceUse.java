package com.wind.spring.spring.autodiscovery;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ResourceUse {
    public MovieFinder movieFinder;
    @Resource(name = "jpaMovieFinder")
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
