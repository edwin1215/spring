package com.edwin.spring.web.jvm.dispatch;

/**
 * 静态分派（Method Overload Resolution）
 * 
 * @author caojunming
 *
 */
public class StaticDispatch {

	public void say(Person p) {
		System.out.println("hello guy");
	}

	public void say(Man m) {
		System.out.println("hello man");
	}

	public void say(Woman w) {
		System.out.println("hello woman");
	}

	public static void main(String[] args) {
		StaticDispatch s = new StaticDispatch();
		Person man = new Man();
		Person woman = new Woman();
		Woman wo = new Woman();
		s.say(man);
		s.say(woman);
		s.say(wo);
	}
}

class Person {

}

class Man extends Person {

}

class Woman extends Person {

}
