package com.edwin.spring.web.concurrent;

public class ThreadJoinTest {


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am t1");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("i am t2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("i am main");

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.start();
        System.out.println(t2.isAlive());
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t2.isAlive());
        System.out.println("all is over");
    }

}
