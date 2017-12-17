package com.edwin.spring.web.algorithm.interesting;

import java.util.Random;

/**
 * 页码统计
 */
public class PageStatistics {
    public static void main(String[] args) {
        Random random = new Random();
        int nextInt = random.nextInt(100);
        System.out.println("nextInt : " + nextInt);
        sum(nextInt, 1);
        for (int i = 1; nextInt / i != 0; i *= 10) {

        }
    }

    public static void sum(int number, int ob) {
        int count = 0;
        int factor = 1;

        int lowNum = 0;
        int currNum = 0;
        int highNum = 0;

        while (number / factor != 0) {
            lowNum = number - (number / factor) * factor;
            currNum = (number / factor) % 10;
            highNum = number / (factor * 10);
            if (currNum < ob) {
                count += highNum * factor;
            } else if (currNum > ob) {
                count += (highNum + 1) * factor;
            } else {
                count += highNum * factor + lowNum + 1;
            }
            factor *= 10;
        }
        System.out.println(count);
    }
}
