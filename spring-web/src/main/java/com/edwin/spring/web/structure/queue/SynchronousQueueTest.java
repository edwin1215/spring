package com.edwin.spring.web.structure.queue;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 *
 *
 * @author caojunming
 * @datetime 2020/10/2 4:46 PM
 */
public class SynchronousQueueTest {

    static ExecutorService e = Executors.newFixedThreadPool(1);
    static SynchronousQueue<String> q = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        e.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);

                    /**
                     * poll和offer是非阻塞，transfer.transfer(e, true, 0)
                     * take和put是阻塞，transfer.transfer(e, false, 0)
                     */
                    PrintUtils.sys("poll:" + q.poll());
//                    PrintUtils.sys("take:" + q.take());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.next();
            q.offer(next);
            PrintUtils.sys("offer:" + q.offer(next) + ",content:" + next);
//            q.put(next);
//            PrintUtils.sys("put:" + next);
        }

//        System.out.println(q.peek());
//        System.out.println(q.poll());
    }
}
