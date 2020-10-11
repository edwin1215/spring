package com.edwin.spring.web.concurrent.tools;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caojunming
 * @datetime 2020/10/2 2:05 PM
 */
public class ExchangerTest {
    private final static int POOL_NUM = 3;

    private static final Exchanger<String> exe = new Exchanger<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(POOL_NUM);

    public static void main(String[] args) {
        pool.execute(() -> {
            String a = "银行流水A";
            try {
                String exchange = exe.exchange(a);
                PrintUtils.sys("A get:" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            String b = "银行流水B";
            try {
                // 任何一个线程未执行exchange方法，两个线程都会阻塞等待
                Thread.sleep(1000);
                String exchange = exe.exchange(b);
                PrintUtils.sys("B:" + b);
                PrintUtils.sys("B get:" + exchange);
                PrintUtils.sys("数据是否一致：" + b.equals(exchange));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        多个线程执行只有一个线程能获取到交换的信息，其他线程阻塞
//        pool.execute(() -> {
//            String c = "银行流水C";
//            try {
//                // 任何一个线程
//                Thread.sleep(1000);
//                String exchange = exe.exchange(c);
//                PrintUtils.sys("C:" + c);
//                PrintUtils.sys("C get:" + exchange);
//                PrintUtils.sys("数据是否一致：" + c.equals(exchange));
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        pool.shutdown();
    }
}
