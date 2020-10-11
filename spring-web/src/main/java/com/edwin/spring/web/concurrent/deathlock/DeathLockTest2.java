package com.edwin.spring.web.concurrent.deathlock;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author caojunming
 * @datetime 2020/10/11 2:47 PM
 */
public class DeathLockTest2 implements Runnable {

    final static ReentrantLock eastLock = new ReentrantLock();
    final static ReentrantLock westLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            PrintUtils.sys("i m running.");
            if (Thread.currentThread().getId() % 2 == 0) {
                eastLock.lockInterruptibly();
                Thread.sleep(500);
                westLock.lockInterruptibly();
            } else {
                westLock.lockInterruptibly();
                Thread.sleep(600);
                eastLock.lockInterruptibly();
            }
            PrintUtils.sys("i can pass!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (eastLock.isHeldByCurrentThread()) {
                eastLock.unlock();
            }
            if (westLock.isHeldByCurrentThread()) {
                westLock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(new DeathLockTest2(), "esst").start();
        new Thread(new DeathLockTest2(), "west").start();
        Thread.sleep(10000);

    }
}
