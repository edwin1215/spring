package com.edwin.spring.web.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * 分析：1.7之后字面量、静态变量从永久代转移到了java堆中，
 *          因此无限生成string，会导致堆内存溢出
 *
 * @author caojunming
 * @datetime 2020/10/1 2:16 PM
 */
public class StringOOMTest {

    static String base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
