package com.edwin.spring.web.concurrent;

/**
 * Syncronized 加在方法上
 */
public class SyncMethodTest {

    public static final int TIME_WAIT =1000 * 5;
    public static synchronized void testSyncOnStaticMethod() {
        System.out.println("testSyncOnStaticMethod");
        try {
            Thread.sleep(TIME_WAIT);
        } catch (InterruptedException e) {
        }
    }

    public static void testSyncOnClass() {
        synchronized (SyncMethodTest.class) {
            System.out.println("testSyncOnClass");
            try {
                Thread.sleep(TIME_WAIT);
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void testSyncOnMethod() {
        System.out.println("testSyncOnMethod");
        try {
            Thread.sleep(TIME_WAIT);
        } catch (InterruptedException e) {
        }
    }

    public void testSyncOnThis() {
        synchronized (this) {
            System.out.println("testSyncOnThis");
            try {
                Thread.sleep(TIME_WAIT);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void case1() {
        // case1  
        // 先输出testSyncOnThis或者testSyncOnMethod  
        // 然后停顿10秒，再输出另一个  
        // 这个现象表明了  

        // public synchronized void func() {  
        // }  

        // 等价于  

        // public void func() {  
        //     synchronized (this) {  
        //     }  
        // }  
        final SyncMethodTest t1 = new SyncMethodTest();
        final SyncMethodTest t2 = new SyncMethodTest();
        new Thread(() -> t1.testSyncOnStaticMethod()).start();

        new Thread(() -> t1.testSyncOnStaticMethod()).start();
    }

    public static void case2() {
        // case2  
        // 先输出testSyncOnClass或者testSyncOnStaticMethod  
        // 然后停顿10秒，再输出另一个  
        // 这个现象表明了  

        // public synchronized static void staticFunc() {  
        // }  

        // 等价于  

        // public static void staticFunc() {  
        //     synchronized (SyncMethodTest.class) {  
        //     }  
        // }  
        new Thread(SyncMethodTest::testSyncOnClass).start();

        new Thread(SyncMethodTest::testSyncOnStaticMethod).start();
    }

    public static void main(String[] args) throws InterruptedException {
//        case1();
       // case2();

        Object lock = new Object();
        synchronized (lock) {
            System.out.println("enter");
            lock.wait();
            System.out.println("wait");
            lock.notify();
            System.out.println("notify");


        }


    }
}
