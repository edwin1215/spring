package com.edwin.spring.web.structure.queue;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.Scanner;

/**
 * 基于Sync实现的blocking queue
 *
 * @author caojunming
 */
public class LockBlockingQueueNew<T> {


    private final static int CAPACITY = 10;
    private final static int INTERVAL = 3000;

    private Object[] con = new Object[CAPACITY];

    private int pollPoint = 0, pushPoint = 0, count = 0;

    public T poll() {
        T result = null;
        synchronized (con) {
            try {
                while (count <= 0) {
                    con.wait();
                }
                PrintUtils.sys("【Poll】当前队列长度：" + count);
                result = (T) con[pollPoint];
                if (++pollPoint == con.length) {
                    pollPoint = 0;
                }
                count--;
                con.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(INTERVAL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;

    }

    public void push(T t) {
        synchronized (con) {
            try {
                while (count >= CAPACITY) {
                    con.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PrintUtils.sys("【Push】当前队列长度：" + count);
            con[pushPoint] = t;
            if (++pushPoint == con.length) {
                pushPoint = 0;
            }
            count++;
            con.notifyAll();
        }
    }

    public static void main(String[] args) {
        LockBlockingQueueNew<String> queue = new LockBlockingQueueNew<>();
        boolean running = true;
        Thread t1 = new Thread(() -> {
            while (running) {
                PrintUtils.sys("【Poll】result：" + queue.poll());
            }
        });

        t1.start();

        Thread t2 = new Thread(() -> {
            while (running) {
                PrintUtils.sys("【Poll】result：" + queue.poll());
            }
        });
//        t2.start();

        while (running) {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s == null || "".equals(s)) {
                continue;
            }
            String[] split = s.split(" ");
            if ("exit".equals(split[0])) {
                PrintUtils.sys("t1:" + t1.isInterrupted());

                continue;
            }


            for (String str : split) {
                queue.push(str);
                PrintUtils.sys("【Push】content: " + str);
            }
        }
    }

}
