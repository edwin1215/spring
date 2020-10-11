package com.edwin.spring.web.concurrent.amino;

/**
 *
 * @author caojunming
 * @datetime 2020/9/28 11:46 AM
 */
public class LockFreeListTest {

    public static void main(String[] args) {
        LockFreeList<String> l = new LockFreeList();
        l.add("hahaha");
        l.add("xixixi");

        String s = l.get(1);
        System.out.println(s);

    }
}
