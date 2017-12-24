package com.edwin.spring.web.designpattern.singleTon;

/**
 * -XX:+TraceClassLoading
 * -XX:+TraceClassUnloading
 */
public class SingleTonTest {

    public static void main(String[] args) {
        System.out.println("======");
        IoDH.sayHello();
        System.out.println("------");
        IoDH.SingleTon singleTon = IoDH.getInstance();
        singleTon.sayHello();
    }
}
