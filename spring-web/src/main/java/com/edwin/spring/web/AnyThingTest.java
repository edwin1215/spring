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

        AtomicStampedReference<Integer> ato = new AtomicStampedReference<>(1, 1);
        ato.compareAndSet(1, 5, 1, 3);

        System.out.println(ato.getReference());
    }

}
