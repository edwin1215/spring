package com.edwin.spring.web.jvm.tiaoyou;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CPUTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "TTTT1111").start();
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "TTTT2222").start();


        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
