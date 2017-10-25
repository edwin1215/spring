package com.edwin.spring.web.kafka;

public class MainTest {

	public static void main(String[] args) {
		String a = "123";
		String b = new String("123");
		String c = "123";
		System.out.println(a == c);
		System.out.println(a.intern() == b);
		System.out.println(b.intern() == a);
		System.out.println(a.intern().equals(b));
		System.out.println(b.intern().equals(a));
		System.out.println(a.getBytes());
		System.out.println(b.getBytes());
		System.out.println(c.getBytes());
		System.out.println("-----------");
		String bb = new String("abc").intern();
		String cc = new String("ab" + "c");
		System.out.println(bb == cc.intern());
	}
}
