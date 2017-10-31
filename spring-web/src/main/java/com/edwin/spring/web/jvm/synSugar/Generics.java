package com.edwin.spring.web.jvm.synSugar;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java泛型与类型擦除
 * 
 * @author caojunming
 * @data 2017年10月22日 上午9:49:02
 */
public class Generics {

//	public static String method(List<String> list) {
//		System.out.println("return string");
//		return "";
//	}

	// 这种方式使用1.6javac编译，Eclipse的ECJ编译器拒绝编译
//	public static int method(List<Integer> list) {
//		System.out.println("return string");
//		return 0;
//	}

	public static void main(String[] args) {
		Long x = 128L;
		Long y = 128L;
		System.out.println(x == y);

		AtomicInteger i = new AtomicInteger(1);
	}
}
