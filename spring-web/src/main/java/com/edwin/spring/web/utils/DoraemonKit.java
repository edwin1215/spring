package com.edwin.spring.web.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哆啦A梦百宝箱
 *
 * @author junming.cao
 * @date 2022/2/22 9:48 下午
 */
@Slf4j
@UtilityClass
public class DoraemonKit {
    private final Random RANDOM = new Random();

    public boolean random(int base, int ratio) {
        return RANDOM.nextInt(base) < ratio;
    }

    public int getRandomNum(int base) {
        return getRandomNum(base, 1);
    }

    /**
     * 你不知道
     *
     * @param base
     * @return
     */
    public int getRandomNumNoZero(int base) {
        return getRandomNumNoZero(base, 1);
    }

    /**
     *
     * @param base
     * @param multiplier
     * @return
     */
    public int getRandomNum(int base, int multiplier) {
        return RANDOM.nextInt(base) * multiplier;
    }

    public int getRandomNumNoZero(int base, int multiplier) {
        return (RANDOM.nextInt(base) + 1) * multiplier;
    }

    /**
     * 睡眠喷雾
     *
     * @param timeout
     */
    public void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("sleep error.", e);
        }
    }
}
