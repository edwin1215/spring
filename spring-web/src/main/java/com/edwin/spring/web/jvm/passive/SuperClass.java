package com.edwin.spring.web.jvm.passive;

/**
 * 被动引用 <所有引用类的方式都不会触发类的初始化>
 * 
 * @author caojunming
 *
 */
public class SuperClass {

	static {
		System.out.println("SuperClass init...");
	}
	public static int value = 520;
}

class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init...");
	}
}