package com.edwin.spring.web.tools;

public class PrintUtils {

    public static void sys(String str) {
        System.out.println("【" + Thread.currentThread().getName() + "】" + str);
    }
    public static void error(String str) {
        System.err.println("【" + Thread.currentThread().getName() + "】" + str);
    }
}
