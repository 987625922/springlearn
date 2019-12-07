package com.wind.java.exception;

public class ExceptionTest {

    public static void main(String args[]) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            try {
                int i = 1 / 0;
            } catch (Exception e1) {
                System.out.println("==>抛出异常2" + e1.getMessage());
            }
            System.out.println("==>抛出异常1" + e.getMessage());
        }

    }

}
