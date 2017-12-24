package com.edwin.spring.web.concurrent;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPoolExecutorTest {

    private static int corePoolSize = 2;
    private static int maximumPoolSize = 5;
    private static long keepAliveTime = 60;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(3));

        System.out.println(Runtime.getRuntime().availableProcessors());

        Future<?> submit = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("executor submit");
                System.out.println(Thread.currentThread().getName());
                return 1;
            }
        });

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
