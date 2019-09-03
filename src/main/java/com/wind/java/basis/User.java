package com.wind.java.basis;

public class User implements Person,PersonTwo {

    @Override
    public void getName() {
        PersonTwo.super.getName();
        System.out.println("user");
    }
}
