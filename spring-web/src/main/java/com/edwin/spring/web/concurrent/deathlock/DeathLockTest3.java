package com.edwin.spring.web.concurrent.deathlock;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 最简单的死锁case
 *
 * @author caojunming
 * @datetime 2020/10/11 2:47 PM
 */
public class DeathLockTest3 {

    final Object a = new Object();
    final Object b = new Object();

    final ReentrantLock al = new ReentrantLock();
    final ReentrantLock bl = new ReentrantLock();

    private void syncDeathLock() throws InterruptedException {
        new Thread(()-> {
            synchronized (a) {
                PrintUtils.sys("get a lock");
                synchronized (b) {
                    PrintUtils.sys("get b lock");
                }
            }
        },"Sync-DeathLockThread-1").start();
        new Thread(()-> {
            synchronized (b) {
                PrintUtils.sys("get b lock");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    PrintUtils.sys("get a lock");
                }
            }
        },"Sync-DeathLockThread-2").start();
    }

    private void lockDeathLock() throws InterruptedException {
        new Thread(()-> {
            al.lock();
            try {
                PrintUtils.sys("get a lock");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bl.lock();
                try {
                    PrintUtils.sys("get b lock");
                } finally {
                    bl.unlock();
                }
            } finally {
               al.unlock();
            }
        },"Lock-DeathLockThread-1").start();
        new Thread(()-> {
            bl.lock();
            try {
                PrintUtils.sys("get b lock");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                al.lock();
                try {
                    PrintUtils.sys("get a lock");
                } finally {
                    al.unlock();
                }
            } finally {
                bl.unlock();
            }
        },"Lock-DeathLockThread-2").start();
    }


    public static void main(String[] args) throws InterruptedException {
        DeathLockTest3 t = new DeathLockTest3();
        t.syncDeathLock();
        t.lockDeathLock();
        Thread.sleep(10000);
    }
}
