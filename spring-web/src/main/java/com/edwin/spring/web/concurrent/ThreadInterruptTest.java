package com.edwin.spring.web.concurrent;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.locks.LockSupport;

/**
 * Interrupt打断当前线程状态
 * sleep、wait、join
 *
 * @author caojunming
 * @datetime 2020/9/7 10:52 AM
 */
public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            PrintUtils.sys("enter");
            LockSupport.parkUntil(System.currentTimeMillis() + 5000);
            if (Thread.currentThread().isInterrupted()) {
                PrintUtils.sys("isInterrupted");
            } else {
                PrintUtils.sys("running");
            }
            PrintUtils.sys("exit");

        });

        thread.start();
        PrintUtils.sys("run");


        PrintUtils.sys("休眠开始");
        Thread.sleep(2000);
        PrintUtils.sys("休眠结束");
        PrintUtils.sys("子线程状态：" + thread.getState());
//        System.out.println(interrupt());

        LockSupport.unpark(thread);


        PrintUtils.sys("main end");

    }

    private static boolean interrupt() {
        return Thread.interrupted();
    }
}
