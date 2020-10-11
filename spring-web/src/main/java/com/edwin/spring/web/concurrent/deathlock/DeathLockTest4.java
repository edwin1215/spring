package com.edwin.spring.web.concurrent.deathlock;

import com.edwin.spring.web.tools.PrintUtils;

/**
 * @author caojunming
 * @datetime 2020/10/11 4:45 PM
 */
public class DeathLockTest4 implements Runnable {

    DeathLockTest4 obj;

    public void setObj(DeathLockTest4 obj) {
        this.obj = obj;
    }

    private synchronized void exe0() throws InterruptedException {
        Thread.sleep(500);

        PrintUtils.sys("start exe0...");
        obj.exe1();
    }

    private synchronized void exe1() throws InterruptedException {
        Thread.sleep(500);

        PrintUtils.sys("start exe1...");
        obj.exe0();
    }

    @Override
    public void run() {
        try {
            if ("exe0".equals(Thread.currentThread().getName())) {
                exe0();
            } else {
                exe1();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeathLockTest4 o1 = new DeathLockTest4();
        DeathLockTest4 o2 = new DeathLockTest4();
        o1.setObj(o2);
        o2.setObj(o1);
        new Thread(o1, "exe0").start();
        new Thread(o2, "exe1").start();
    }
}
