package com.edwin.spring.web.jvm.passive;

/**
 * 类的初始化<clinit>方法
 * 
 * @author caojunming
 *
 */
public class ClassInit {

	static {
		i = 1;
		// System.out.println(i); // 非法向前引用
	}

	public static int i = 520;
}
