package com.edwin.spring.web.concurrent;

/**
 *
 *
 * @author caojunming
 * @datetime 2020/9/7 11:08 AM
 */
public class ThreadJoinTest {


    public static void main(String[] args) {
        Thread t2 = new Thread(() -> {
            sys("i am t2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sys("t2 over");
        });


        Thread t1 = new Thread(() -> {
            sys("i am t1");
            if (t2.isAlive()) {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sys("t1 over!");
        });



        sys("i am main");

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t1.start();
        t2.start();
        t2.setPriority(10);
        sys(t2.isAlive());
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sys(t2.isAlive());
        sys("all is over");
    }

    public static void sys(Object out) {
        System.out.println(Thread.currentThread().getName() + ":" + out);
    }
}
