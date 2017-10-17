package com.edwin.spring.web.jvm.passive;

/**
 * 被动引用 <所有引用类的方式都不会触发类的初始化>
 * 
 * @author caojunming
 *
 */
public class ConstClass {

	static {
		System.out.println("ConstClass init...");
	}

	public final static String SPEAK = "Hello world";
}
