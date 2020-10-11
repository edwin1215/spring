package com.edwin.spring.web.concurrent.tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    private static final int THREAD_NUM = 2;
    static CountDownLatch latch = new CountDownLatch(THREAD_NUM);
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);

    public static void main(String[] args) {

        for (int i = 0; i < THREAD_NUM; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " run complete");
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i run 3");

        executorService.shutdown();
    }
}
