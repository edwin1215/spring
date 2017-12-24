package com.edwin.spring.web.designpattern.singleTon;

public class IoDH {

    private IoDH() {

    }

    static class SingleTon {
        private SingleTon() {

        }
        private final static SingleTon singleTon = new SingleTon();
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    public static SingleTon getInstance() {
        return SingleTon.singleTon;
    }

    public static void sayHello() {
        System.out.println("IoDH hello world");
    }

    public static void main(String[] args) {
        SingleTon s1, s2;
        s1 = getInstance();
        s2 = getInstance();
        System.out.println(s1 == s2);
    }

}
