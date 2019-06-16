package com.wind.spring.spring.autodiscovery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

//自定义命名，指定类在装载到Bean容器中的id
@Scope("prototype")
@Repository("moviefinderimpl")
public class MovieFinderImpl implements MovieFinder{}
