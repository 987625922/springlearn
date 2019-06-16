package com.wind.spring.spring.autodiscovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@Component
public class RequiredMovieLister {
    private MovieFinderImpl movieFinder;
    @Autowired
    public void setMovieFinder(MovieFinderImpl movieFinder) {
        this.movieFinder = movieFinder;
    }
}
