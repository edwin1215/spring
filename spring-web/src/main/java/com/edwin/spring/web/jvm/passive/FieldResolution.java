package com.edwin.spring.web.jvm.passive;

public class FieldResolution {

    interface Inter0 {
        int a = 0;
    }

    interface Inter1 extends Inter0 {
        int a = 1;
    }

    interface Inter2 {
        int a = 2;
    }

    static class Parent implements Inter1 {
        public static int a = 3;
    }

    static class Sub extends Parent implements Inter2 {
        public static int a = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.a);
    }
}
