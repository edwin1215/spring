package com.edwin.spring.web.concurrent;

import java.util.concurrent.*;

public class ExchangerTest {

    private static final Exchanger<String> exc = new Exchanger<>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String a = "银行流水A";
                try {
                    Thread.sleep(3000);
                    String exchange = exc.exchange(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String b = "银行流水B";
                    String a = null;
                    a = exc.exchange("a");
                    // a = exc.exchange("fd", 1000, TimeUnit.MILLISECONDS);
                    System.out.println(b + "|" + a + "|" + a.equals(b));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
}
