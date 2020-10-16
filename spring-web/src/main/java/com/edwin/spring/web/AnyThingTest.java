package com.edwin.spring.web;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AnyThingTest {

    static int a = 10;

    public synchronized void add() {
        a++;
        System.out.println("sync");
    }

    public static synchronized void add1() {
        a++;
        System.out.println("static sync");
    }

    public static void main(String args[]) {

//        AtomicStampedReference<Integer> ato = new AtomicStampedReference<>(1, 1);
//        ato.compareAndSet(1, 5, 1, 3);
//        System.out.println(ato.getReference());

        int c = 1;
        int SHARED_SHIFT = 16;
        int SHARED_UNIT = (1 << SHARED_SHIFT);
        int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
        int MAX_COUNT = (1 << SHARED_SHIFT) - 1;

        System.out.println(SHARED_SHIFT);
        System.out.println(EXCLUSIVE_MASK);
        System.out.println(MAX_COUNT);

        System.out.println(c >>> SHARED_SHIFT);
        System.out.println(SHARED_UNIT >>> SHARED_SHIFT);
        System.out.println(c & EXCLUSIVE_MASK);

    }

}
