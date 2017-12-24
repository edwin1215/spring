package com.edwin.spring.web.concurrent;

import java.util.HashMap;
import java.util.UUID;

public class ConcurrentHashMapTest {

    public static void testHashMap() {
        final HashMap<String, String> hMap = new HashMap<>(2);
        Thread ftf = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            hMap.put(UUID.randomUUID().toString(), "");
                        }
                    }, "subThread" + i).start();
                }
            }
        }, "ftf");
        ftf.start();
        try {
            ftf.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("the end");
    }

    public static void main(String[] args) {
        testHashMap();
    }
}
