package com.edwin.spring.web.jvm.dynType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * invokedynamic指令查看
 * #javap -verbose InvokeDynamicTest
 *
 * @author caojunming
 * @datetime 2020/9/5 11:58 PM
 */
public class InvokeDynamicTest {

    public static void main(String[] args) throws Throwable {
        List<String> list = new ArrayList<>();
        list.add("da");
        list.add("ha");
        list.add("ma");

        List<String> collect = list.stream().map(InvokeDynamicTest::convert).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static String convert(String s) {
        return s + "|";
    }
}
