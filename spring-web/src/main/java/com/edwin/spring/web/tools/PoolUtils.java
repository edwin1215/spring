package com.edwin.spring.web.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caojunming
 * @datetime 2020/10/11 8:14 PM
 */
public class PoolUtils {

    static volatile ExecutorService fixedPool;
    static volatile ExecutorService cachePool;
    static volatile ExecutorService singlePool;

    public static ExecutorService getFixedPool(int num) {
        return getFixedPool(num, "Fixed-TPool");
    }

    public static ExecutorService getFixedPool(int num, final String prefix) {
        if (fixedPool == null) {
            synchronized (PoolUtils.class) {
                if (fixedPool == null) {
                    fixedPool = Executors.newFixedThreadPool(num, new CustomFactory(prefix));
                }
            }
        }
        return fixedPool;
    }

    public static ExecutorService getCachedPool() {
        return getCachedPool("Cache-TPool");
    }

    public static ExecutorService getCachedPool(final String prefix) {
        if (cachePool == null) {
            synchronized (PoolUtils.class) {
                if (cachePool == null) {
                    cachePool = Executors.newCachedThreadPool(new CustomFactory(prefix));
                }
            }
        }
        return cachePool;
    }

    public static ExecutorService getSinglePool() {
        return getSinglePool("Single-TPool");
    }

    public static ExecutorService getSinglePool(final String prefix) {
        if (singlePool == null) {
            synchronized (PoolUtils.class) {
                if (singlePool == null) {
                    singlePool = Executors.newSingleThreadExecutor(new CustomFactory(prefix));
                }
            }
        }
        return singlePool;
    }

    public static void release() {
        if (fixedPool != null) {
            fixedPool.shutdownNow();
        }
        if (cachePool != null) {
            cachePool.shutdownNow();
        }
        if (singlePool != null) {
            singlePool.shutdownNow();
        }
    }

    static class CustomFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group = Thread.currentThread().getThreadGroup();
        final String prefix;

        CustomFactory(final String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    prefix + "-" + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
