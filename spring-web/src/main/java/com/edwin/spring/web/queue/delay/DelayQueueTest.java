package com.edwin.spring.web.queue.delay;

import com.alibaba.fastjson.JSON;
import com.edwin.spring.web.utils.DoraemonKit;
import com.edwin.spring.web.utils.PrintUtil;

import java.util.concurrent.DelayQueue;

/**
 *
 * @author edwin
 * @date 2021/12/4 6:07 下午
 */
public class DelayQueueTest {

    private static final DelayQueue<DelayCell<BusinessCell>> queue = new DelayQueue<>();

    private static void createDelayQueue() {
        for (int i = 0; i < 5; i++) {
            queue.put(createDelayCell(i + 1));
        }
    }

    public static void main(String[] args) throws Exception {
        createDelayQueue();
        PrintUtil.printTime("-------start consume-------");
        consume();


    }

    private static void consume() throws InterruptedException {
        while (true) {
            /*
             * 取队列头部元素是否过期
             */
            DelayCell<BusinessCell> poll = queue.take();
            PrintUtil.printTime("main:" + JSON.toJSON(poll));
//            TimeUnit.MILLISECONDS.sleep(100);
        }
    }


    private static DelayCell<BusinessCell> createDelayCell(int id) {
        int delayTime = DoraemonKit.getRandomNumNoZero(5, 1000);
        PrintUtil.printTime("currentId:" + id + ", delayTime:" + delayTime);
        return new DelayCell<>(new BusinessCell(id, "name-" + id, 1), delayTime + System.currentTimeMillis(), id);
    }


}
