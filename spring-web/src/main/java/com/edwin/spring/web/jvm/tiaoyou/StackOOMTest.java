package com.edwin.spring.web.jvm.tiaoyou;

import com.edwin.spring.web.tools.PrintUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caojunming
 * @datetime 2020/10/12 8:14 PM
 */
public class StackOOMTest {

    private AtomicInteger i = new AtomicInteger();

    private void stackTest() {
        i.getAndIncrement();
        stackTest();
    }

    public static void main(String[] args) {
        StackOOMTest t = new StackOOMTest();
        try {
            t.stackTest();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PrintUtils.sys("times:" + t.i.get());
        }
    }
}
