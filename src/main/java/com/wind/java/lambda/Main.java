package com.wind.java.lambda;

import com.wind.spring.bean.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/*
* lambda表达式的使用
* */
public class Main {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "spring", "and", "javaweb");
        //函数接口
//        list.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });
        list.forEach(s -> {
            System.out.println(s);
        });
        //方法引用
        list.forEach(System.out::println);
        //匿名内部类
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("==>");
//            }
//        }).start();
        new Thread(() -> {
            System.out.println("lambda表达式");
        }).start();

        //Optional 避免空指针
        Optional.of(new Book()).map(Book::getName).ifPresent(System.out::println);
    }


}
