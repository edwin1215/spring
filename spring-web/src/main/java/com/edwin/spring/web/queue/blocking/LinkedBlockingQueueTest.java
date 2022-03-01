package com.edwin.spring.web.queue.blocking;

import com.edwin.spring.web.utils.DoraemonKit;
import com.edwin.spring.web.utils.PrintUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK原生阻塞队列
 *
 * |      | 阻塞 | 非阻塞 |
 * | ---- | ---- | ------ |
 * | 插入 | put  | take   |
 * | 弹栈 | poll | offer  |
 * | 查看 |      | peek   |
 *
 * @author caojunming
 * @date 2021/12/04
 */
@Slf4j
public class LinkedBlockingQueueTest {

    public static final BlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<>(10);
    public static final AtomicInteger COUNTER = new AtomicInteger();

    public static void main(String[] args) {

        new Thread(new ConsumerClient()).start();
        new Thread(new ConsumerClient()).start();

        while (true) {
            int increment = COUNTER.getAndIncrement();
            // offer方法不阻塞，插入失败直接返回false
//                QUEUE.offer(increment);
            try {
                // put方法阻塞等待
                QUEUE.put(increment);
                PrintUtil.printTime("插入：" + increment);
                TimeUnit.MILLISECONDS.sleep(DoraemonKit.getRandomNumNoZero(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ConsumerClient implements Runnable {

        @Override
        public void run() {
            while (true) {
                // poll方法不阻塞，获取失败直接返回false
//                QUEUE.poll(COUNTER.getAndIncrement());
                try {
                    // take方法阻塞等待
                    PrintUtil.printTime("---取出：" + QUEUE.take());
                    TimeUnit.MILLISECONDS.sleep(DoraemonKit.getRandomNumNoZero(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
