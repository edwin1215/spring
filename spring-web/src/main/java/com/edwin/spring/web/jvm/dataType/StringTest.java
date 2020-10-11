package com.edwin.spring.web.jvm.dataType;

public class StringTest {

    public static void main(String[] args) {
        String a1 = "123";
        String b1 = "123";
        System.out.println(System.identityHashCode(a1));
        System.out.println(System.identityHashCode(b1));
        System.out.println(a1 == b1);
        System.out.println("1---------------------");

        String a2 = new String("123");
        String b2 = "123";
        System.out.println(System.identityHashCode(a2));
        System.out.println(System.identityHashCode(b2));
        System.out.println(a2 == b2);
        System.out.println(a2.equals(b2));
        System.out.println("2---------------------");

        String a3 = new String("1234").intern();
        String b3 = "1234";
        System.out.println(System.identityHashCode(a3));
        System.out.println(System.identityHashCode(b3));
        System.out.println(a3 == b3);
        System.out.println("3---------------------");


        String b4 = new String("12345");
        b4.intern();
        String a4 = "12345";
        System.out.println(System.identityHashCode(b4));
        System.out.println(System.identityHashCode(a4));
        System.out.println(a4 == b4);
        System.out.println("4---------------------");

        StringTest t = new StringTest();
        System.out.println(t);

        String te = new String("980");
        System.out.println(te.hashCode());


        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        System.out.println("----------------------------");
//        String ss1 = new StringBuilder("合法我积分").append("我给大哥").toString();
//        System.out.println(ss1.intern() == ss1);
//        String ss11 = new StringBuilder("合法我积分").append("我给大哥").toString();
//        System.out.println(ss11.intern() == ss11);
        String ps = "合法我积分我给大哥";
        String ss2 = new String(ps);
        System.out.println(ss2.intern() == ps);
        System.out.println(ss2.intern() == ss2);
        String ss3 = new String("合法我积分我给大哥");
        System.out.println(ss3.intern() == ss3);

    }
}
