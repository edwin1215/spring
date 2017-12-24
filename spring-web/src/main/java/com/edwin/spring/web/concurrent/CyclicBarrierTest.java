package com.edwin.spring.web.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierTest implements Runnable {

    private CyclicBarrier c = new CyclicBarrier(4, this);

    private  ExecutorService executorService = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            final int a = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(), a + 1);
                    try {
                        c.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        AtomicInteger i = new AtomicInteger(0);
        map.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey() + ":" + e.getValue());
            i.addAndGet(e.getValue());
        });
        System.out.println("result:" + i.get());
    }

    public static void main(String[] args) {
        CyclicBarrierTest t = new CyclicBarrierTest();
        t.count();
    }
}
