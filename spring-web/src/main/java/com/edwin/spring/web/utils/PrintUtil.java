package com.edwin.spring.web.utils;


/**
 * @author caojunming
 * @date 2021/7/21
 */
public class PrintUtil {

    public static void print(Object object) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + object);
    }

    public static void printTime(Object object) {
        System.out.println(DateUtils.nowLocalDateTime(DateStyleEnum.YYYY_MM_DD_HH_MM_SS_SSS) + " - [" + Thread.currentThread().getName() + "] " + object);
    }
}