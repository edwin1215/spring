package com.edwin.spring.web.jvm.oom;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/caojunming/Downloads/logs
 * @author caojunming
 * @datetime 2020/8/23 5:05 PM
 */
public class HeapOOM {
    static class OOMObject { }


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        List<OOMObject> list = Lists.newArrayList();
        int counter = 0;
        try {
            while (true) {
                counter++;
                list.add(new OOMObject());
            }
        } finally {
            System.out.println(counter);
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
