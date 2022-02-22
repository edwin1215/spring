package com.edwin.spring.web.utils;


import java.util.Random;

/**
 * @author caojunming
 * @date 2021/7/21
 */
public class PrintUtil {

    private final static Random RANDOM;

    static {
        RANDOM = new Random();
    }

    public static void print(Object object) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + object);
    }

    public static void printTime(Object object) {
        System.out.println(DateUtils.nowLocalDateTime(DateStyleEnum.YYYY_MM_DD_HH_MM_SS_SSS) + " - [" + Thread.currentThread().getName() + "] " + object);
    }

    public static boolean random(int base, int ratio) {
        return RANDOM.nextInt(base) < ratio;
    }

    public static int getRandomNum(int base) {
        return getRandomNum(base, 1);
    }

    public static int getRandomNumNoZero(int base) {
        return getRandomNumNoZero(base, 1);
    }

    public static int getRandomNum(int base, int multiplier) {
        return RANDOM.nextInt(base) * multiplier;
    }

    public static int getRandomNumNoZero(int base, int multiplier) {
        return (RANDOM.nextInt(base) + 1) * multiplier;
    }
}