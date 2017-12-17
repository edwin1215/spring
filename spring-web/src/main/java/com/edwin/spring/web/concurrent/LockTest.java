package com.edwin.spring.web.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public static void main(String[] args) {
        final LockTest test = new LockTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.lock.lock();
                    System.out.println("t1 lock c1 await");
                    test.c1.await();
                    test.c2.signal();
                    System.out.println("c1 week up");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    test.lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.lock.lock();
                    System.out.println("t2 lock c2 await");
                    test.c2.await();
                    test.c1.signal();
                    System.out.println("c2 week up");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    test.lock.unlock();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.lock.lock();
                    System.out.println("t3 signal");
                    test.c2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    test.lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }


}
