package com.wind.spring.springbean.autodiscovery;

import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class SimpleMovieLister {
    private MovieFinder movieFinder;

    @Autowired
    public SimpleMovieLister(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
