package com.wind.spring.springaop.introductions;

public class FitImpl  implements Fit{
    @Override
    public void filter() {
        System.out.println("FitImpl filter.");
    }
}
