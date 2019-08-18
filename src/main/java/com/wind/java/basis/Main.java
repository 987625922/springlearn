package com.wind.java.basis;

public class Main {

    public static void main(String[] args){
        print("输出","可变参数");

    }
    /**
     * 可变参数使用
     *
     */
    public static void print(String...out){
        StringBuffer sb = new StringBuffer();
        for (String str:out){
            sb.append(str);
        }
        System.out.println("可变参数使用："+sb.toString());
    }
}
