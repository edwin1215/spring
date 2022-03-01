package com.edwin.spring.web.queue.delay;


import com.edwin.spring.web.utils.PrintUtil;
import io.netty.util.HashedWheelTimer;

/**
 * Netty - HashedWheelTimer
 * 轮次时间轮算法实现的。轮次、多层
 * MPSC队列：多生产单消费的无锁队列
 *  Multiple
 *  Producer
 *  Single
 *  Consumer
 *
 * @author junming.cao
 * @date 2021/12/4 6:36 下午
 */
public class NettyHashedWheelTimerTest {
    public static final HashedWheelTimer TIMER = new HashedWheelTimer();

    public static void main(String[] args) {

        TIMER.start();
        for (int i = 0; i < 10; i++) {
//            TIMER.newTimeout()
        }

        PrintUtil.print(normalizeTicksPerWheel(9));
    }


    private static int normalizeTicksPerWheel(int ticksPerWheel) {
        int normalizedTicksPerWheel = 1;
        while (normalizedTicksPerWheel < ticksPerWheel) {
            normalizedTicksPerWheel <<= 1;
        }
        return normalizedTicksPerWheel;
    }
}
