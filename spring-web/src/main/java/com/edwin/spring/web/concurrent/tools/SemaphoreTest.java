package com.edwin.spring.web.concurrent.tools;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * @author caojunming
 * @datetime 2020/10/2 1:52 PM
 */
public class SemaphoreTest {

    private final static int THREAD_NUM = 30;
    private final static int POOL_NUM = 10;
    static ExecutorService pool = Executors.newFixedThreadPool(POOL_NUM);
    static Semaphore sem = new Semaphore(POOL_NUM);
    public static void main(String[] args) {

        for (int i = 0; i <THREAD_NUM; i++) {
            final int a = i + 1;
            try {
                // 允许通过
                sem.acquire();
                pool.execute(() -> {
                    PrintUtils.sys("my thread num is " + a);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                // 释放名额
                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }
}
