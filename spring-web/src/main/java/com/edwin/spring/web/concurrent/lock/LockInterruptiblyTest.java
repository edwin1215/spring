package com.edwin.spring.web.concurrent.lock;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author caojunming
 * @datetime 2020/10/11 4:56 PM
 */
public class LockInterruptiblyTest {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SleepBlocked implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                PrintUtils.error("InterruptedException");
            }
            PrintUtils.sys("Exiting SleepBlocked.run()");

        }
    }

    static class SynchronizedBlocked implements Runnable {

        public synchronized void f() {
            PrintUtils.sys("yield");
            while (true) { // Never release lock
//                PrintUtils.sys("yield");
                Thread.yield();
            }
        }
        public SynchronizedBlocked() {
            PrintUtils.sys("new SynchronizedBlocked()");
            new Thread(() -> f(), "构造方法启动线程").start();
        }

        @Override
        public void run() {
            PrintUtils.sys("Trying to call f()");
            f();
            PrintUtils.sys("Exiting SynchronizedBlocked.run()");

        }
    }

    static class RunnableBlocked implements Runnable {
        static void f() {
            Thread t = Thread.currentThread();
            while (!t.isInterrupted()) {

                Thread.yield();
            }
            PrintUtils.sys("t.isInterrupted():" + t.isInterrupted());
            PrintUtils.sys("Thread.interrupted():" + Thread.interrupted());
            PrintUtils.sys("Thread.interrupted():" + Thread.interrupted());
            PrintUtils.sys("t.isInterrupted():" + t.isInterrupted());
        }

        @Override
        public void run() {
            PrintUtils.sys("Trying to call f()");
            f();
            PrintUtils.sys("exiting RunnableBlocked.run()");
        }
    }

    static void interrupt(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(1000);
        PrintUtils.sys("interrupting " + r.getClass().getSimpleName());
        // t.interrupt();
        PrintUtils.sys(r.getClass().getSimpleName() + " cancel result " + f.cancel(true));
        PrintUtils.sys("interrupt sent to " + r.getClass().getSimpleName());
        exec.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
//        interrupt(new SleepBlocked());
//        interrupt(new SynchronizedBlocked());
        interrupt(new RunnableBlocked());
    }
}
