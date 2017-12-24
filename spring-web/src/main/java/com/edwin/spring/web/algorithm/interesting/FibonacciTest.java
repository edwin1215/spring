package com.edwin.spring.web.algorithm.interesting;

/**
 * 斐波那契数列
 * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233...
 */
public class FibonacciTest {

    public static void main(String[] args) {
        int idx = 12455;
        System.out.println(getNum(idx));
        // System.out.println(recursion(idx));
    }

    public static long getNum(int idx) {
        if (idx < 0) {
            return -1;
        }
        int i = 0;
        long a = 1;
        long b = 1;
        long sum = 1;
        while (i <= idx) {
            if (i > 1) {
                sum = a + b;
                a = b;
                b = sum;
            }
            i++;
        }
        return sum;
    }

    public static long recursion(int idx) {
        if (idx < 0)
            return -1L;
        if (idx <= 1) {
            return 1L;
        } else {
            return recursion(idx - 1) + recursion(idx - 2);
        }
    }
}
