package com.edwin.spring.web.concurrent.deathlock;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 属于业务死锁
 *
 * 哲学家就餐
 *
 * <p>
 *     <h1>描述：</h1>
 *          N个哲学家围在圆桌就餐，因为拮据只有N根筷子，每人之间放一根，
 *              他们在就餐时还会思考：
 *          1. 当他们思考时，不需要任何共享资源
 *          2. 当他们就餐时需要使用筷子，必须同时获取左右两边的两个筷子才能吃放，
 *              否则需要等待其他哲学家方法筷子，才能拿起
 *     <h1>死锁场景：</h1>
 *          每个哲学家同时拿起左边或者同时拿起右边的筷子时，出现死锁
 *     <h1>死锁条件：</h1>
 *          1. 互斥条件：至少有一个资源是不能被共享的，一个Chopstick一次只能被一个Philosopher使用
 *          2. 有一等一：有一个资源，并且等待一个资源(多次执行同步任务导致)
 *          3. 不能抢占：任务不能抢占必须把资源释放当做普通事件
 *          4. 循环等待：条件2的增强
 * </p>
 *
 * @author caojunming
 * @datetime 2020/9/12 10:59 AM
 */
public class DeathLockTest {

    public static void main(String[] args) throws InterruptedException {
        int ponderTime = 0;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            private final ThreadGroup group = Thread.currentThread().getThreadGroup();

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(group, r,
                        "deathLock-ThreadPool-" + threadNumber.getAndIncrement(),
                        0);
                if (t.isDaemon())
                    t.setDaemon(false);
                if (t.getPriority() != Thread.NORM_PRIORITY)
                    t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        });
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(
                    chopsticks[i], chopsticks[(i + 1) % size], i, ponderTime));
        }
        TimeUnit.SECONDS.sleep(100000);
        exec.shutdownNow();
    }


}

/**
 * 筷子类
 */
class Chopstick {
    private boolean taken = false;
    private ReentrantLock lock = new ReentrantLock();
    Condition con = lock.newCondition();

    /**
     * 拿起，同时间只能被一个哲学家拿起
     *
     * @throws InterruptedException
     */
    public void take() throws InterruptedException {

        lock.lock();
        try {
            if (taken) {
                con.await(1000, TimeUnit.SECONDS);
            }
            taken = true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 放下
     */
    public void drop() {
        lock.lock();
        try {
            taken = false;
            con.signal();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 哲学家类
 */
class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private int id;
    // 思考真想
    private int ponderFactor;

    private Random ran = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(ran.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {

        try {
            while(!Thread.interrupted()) {
                PrintUtils.sys(this + " 思考");
                pause();
                left.take();
                PrintUtils.sys(this + " 拿起左边的筷子");
                right.take();
                PrintUtils.sys(this + " 拿起右边的筷子");
                PrintUtils.sys(this + " 吃饭");
                pause();
                PrintUtils.sys(this + " 放下筷子");
                left.drop();
                right.drop();
            }
        } catch (InterruptedException e) {
            PrintUtils.sys(this + " exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher-" + id;
    }
}