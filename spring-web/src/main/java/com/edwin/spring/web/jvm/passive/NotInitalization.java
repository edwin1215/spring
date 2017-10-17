package com.edwin.spring.web.jvm.passive;

/**
 * 被动引用 <所有引用类的方式都不会触发类的初始化>
 * 
 * @author caojunming
 *
 */
public class NotInitalization {

	public static void main(String[] args) {
		// 1、通过子类引用父类的静态属性，不会触发子类初始化
		System.out.println(SubClass.value);
		System.out.println("-----------------------");
		// 2、通过数组定义来引用类，不会触发此类的初始化
		SubClass[] sc = new SubClass[10];
		System.out.println(sc);
		System.out.println("-----------------------");
		// 3、常量在编译期保存在调用类的常量池中，调用是没有引用定义常量的类，不会触发类的初始化
		System.out.println(ConstClass.SPEAK);
	}
}
