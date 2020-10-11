package com.edwin.spring.web.concurrent.tools;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

    static CyclicBarrier barrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(() -> {
            PrintUtils.sys("1.1");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            PrintUtils.sys("1.2");
        }, "SUB").start();


        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        PrintUtils.sys("2");

    }

}

class A implements Runnable {
    @Override
    public void run() {
        PrintUtils.sys("3");
    }
}