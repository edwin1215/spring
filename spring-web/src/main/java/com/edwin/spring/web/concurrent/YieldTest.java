package com.edwin.spring.web.concurrent;

import com.edwin.spring.web.tools.PoolUtils;
import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class YieldTest implements Runnable {

    /**
     * 给调度程序的一个提示，即当前线程愿意放弃其当前使用的处理器。
     * 调度程序可以忽略此提示。
     *
     * <p>Yield是一种启发式尝试，旨在改善线程之间的相对进程，否则会
     * 过度使用CPU。它的使用应该与详细的分析和基准测试相结合，以确保
     * 它确实具有预期的效果。<p/>
     *
     * <p>使用这种方法很少合适。它可能对调试或测试有用，因为它可能有
     * 助于重现由于竞争而产生的bug条件。它在设计并发控制结构时也可能
     * 有用（如java.util.concurrent.locks包中的结构）<p/>
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedPool = PoolUtils.getCachedPool("Yield-Test");
        cachedPool.execute(new YieldTest());
        cachedPool.execute(new YieldTest());
        TimeUnit.SECONDS.sleep(5);
        PoolUtils.release();
    }

    private final static int NUM = 100;
    private static AtomicInteger ai = new AtomicInteger(0);

    @Override
    public void run() {
        PrintUtils.sys("我开始执行了");
        for (int i = 0; i < NUM; i++) {
            int increment = ai.getAndIncrement();
            PrintUtils.sys("执行：" + increment);
            if (increment % 10 == 0) {
                Thread.yield();
            }
        }
    }
}
