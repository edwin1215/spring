package com.edwin.spring.web.concurrent.threadPool;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    private final static int THREAD_NUM = 30000;
    private final static int POOL_NUM = 10;

    private void fixed() {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_NUM);
        for (int i = 0; i <THREAD_NUM; i++) {
            final int a = i + 1;
            pool.execute(() -> {
                PrintUtils.sys("my thread num is " + a);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }

    /**
     *
     */
    private void cache() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i <THREAD_NUM; i++) {
            final int a = i + 1;
            pool.execute(() -> {
                PrintUtils.sys("my thread num is " + a);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }
    public static void main(String[] args) {

        ThreadPoolTest t = new ThreadPoolTest();
        t.fixed();
    }
}
