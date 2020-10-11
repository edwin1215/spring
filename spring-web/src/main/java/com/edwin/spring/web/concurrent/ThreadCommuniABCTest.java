package com.edwin.spring.web.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程依次循环输出ABC，顺序不可变
 *
 * @author caojunming
 * @datetime 2020/9/28 10:33 AM
 */
public class ThreadCommuniABCTest {

    private final static int TIME = 10;
    private volatile boolean state = false;

    private void syncWaitNotify() {
        final Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < TIME; i++) {
                    System.out.println(Thread.currentThread().getName());
                    if (!state) {
                        state = true;
                    }
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }, "A");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                if (!state) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < TIME; i++) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        }, "B");

        t2.start();
        t1.start();
    }

    private void lockImpl() {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {

                for (int i = 0; i < TIME; i++) {
                    System.out.println(Thread.currentThread().getName());
                    if (!state) {
                        state = true;
                    }
                    try {
                        c2.signal();
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                c2.signalAll();
            } finally {
                lock.unlock();
            }
        }, "A");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                if (!state) {
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < TIME; i++) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        c3.signal();
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                c3.signalAll();
            } finally {
                lock.unlock();
            }
        }, "B");

        Thread t3 = new Thread(() -> {
            lock.lock();
            try {
                if (!state) {
                    try {
                        c3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < TIME; i++) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        c1.signal();
                        c3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                c1.signalAll();
            } finally {
                lock.unlock();
            }
        }, "C");
        t3.start();
        t2.start();
        t1.start();

    }

    private void cyclicBarrier() {
        final CyclicBarrier barrier = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < TIME; i++) {
                System.out.println(Thread.currentThread().getName());
//                barrier.await()
            }
        }, "A");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < TIME; i++) {
                System.out.println(Thread.currentThread().getName());
            }
        }, "B");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < TIME; i++) {
                System.out.println(Thread.currentThread().getName());
            }
        }, "C");
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadCommuniABCTest t = new ThreadCommuniABCTest();
        t.syncWaitNotify();
//        t.lockImpl();
    }

}
