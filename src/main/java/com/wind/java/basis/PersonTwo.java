package com.wind.java.basis;

public interface PersonTwo {
    default void getName() {
        System.out.println("persontwo");
    }
}
