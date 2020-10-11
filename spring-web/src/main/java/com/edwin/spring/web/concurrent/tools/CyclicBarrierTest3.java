package com.edwin.spring.web.concurrent.tools;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.*;

/**
 * reset用法
 *
 * @author caojunming
 * @datetime 2020/10/2 12:35 PM
 */
public class CyclicBarrierTest3 {
    private static final int NUM = 3;

    static ExecutorService executor = Executors.newFixedThreadPool(NUM);
    static CyclicBarrier barrier = new CyclicBarrier(NUM, () -> {
        PrintUtils.sys("完成");
    });


    private void exec() {
        for (int i = 0; i < NUM; i++) {
            final int a = i;
            executor.execute(() -> {
                PrintUtils.sys(String.valueOf(a + 1));
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest3 t = new CyclicBarrierTest3();
        for (int i = 0; i < 4; i++) {
            t.exec();
            barrier.reset();
            PrintUtils.sys("完成第" + (i + 1) + "轮");
        }
        executor.shutdown();
//        barrier.wait();
//        barrier.reset();
    }
}
