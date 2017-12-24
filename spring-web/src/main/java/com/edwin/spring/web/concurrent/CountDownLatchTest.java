package com.edwin.spring.web.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    static CountDownLatch latch = new CountDownLatch(2);

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("i run 1");
                latch.countDown();
                System.out.println("i run 2");
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i run 3");

        executorService.shutdown();
    }
}
