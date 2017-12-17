package com.edwin.spring.web.concurrent;

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("123");
        local.get();
    }
}
