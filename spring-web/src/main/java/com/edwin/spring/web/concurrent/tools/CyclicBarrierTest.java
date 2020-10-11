package com.edwin.spring.web.concurrent.tools;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest implements Runnable {

    private static final int PARTIES = 4;

    private CyclicBarrier barrier = new CyclicBarrier(PARTIES, this);

    private static ExecutorService executorService = Executors.newFixedThreadPool(PARTIES);

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < PARTIES; i++) {
            final int a = i;
            executorService.execute(() -> {
                PrintUtils.sys("start");
                map.put(Thread.currentThread().getName(), a + 1);
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PrintUtils.sys("end");
            });
        }
    }

    @Override
    public void run() {
        AtomicInteger i = new AtomicInteger(0);
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
            i.addAndGet(value);
        });
        PrintUtils.sys("result:" + i.get());
    }

    public static void main(String[] args) {
        CyclicBarrierTest t = new CyclicBarrierTest();
        t.count();
        executorService.shutdown();
    }
}
