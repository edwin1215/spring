package com.edwin.spring.web.concurrent;

/**
 * wait方法
 */
public class ThreadWaitTest {

    // 所对象
    Object lock = new Object();

    public static void main(String[] args) {
        final ThreadWaitTest test = new ThreadWaitTest();

        // t1线程，调用waiting方法
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.waiting();
            }
        });

        // t2线程，调动sleep方法，休眠5秒，然后唤醒t1
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.sleep();
            }
        });

        // 启动t1线程
        t1.start();
        // t11.start();
        // 休眠0.1秒钟，让waiting方法先执行
        pause(100);
        // 启动t2线程
        t2.start();
    }

    public void waiting() {
        System.out.println("begin waiting");
        synchronized (lock) {
            try {
                long now = System.currentTimeMillis();
                // 调用wait方法时，对象必须被加锁，会释放对象上的锁
                // lock.wait();
                // 超过时间后，自动唤醒，但是需要重新获取锁
                lock.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end waiting");
    }

    public void sleep() {
        System.out.println("begin sleep");
        // t1线程wait，t2线程获取锁
        synchronized (lock) {
            try {
                // 休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 随机唤醒一个waiting线程
            // lock.notify();
            // sleep结束后唤醒所有waiting线程
            lock.notifyAll();
        }
        System.out.println("end sleep");
    }

    public static void pause(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
