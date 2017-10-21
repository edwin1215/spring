package com.edwin.spring.web.jvm.proxy;

/**
 * 普通的代理模式
 * 
 * @author caojunming
 * @data 2017年10月21日 下午7:19:46
 */
public class PersonProxy {

	private Person person;
	
	public PersonProxy(Person person) {
		this.person = person;
	}

	/**
	 * before
	 */
	public void cook() {
		System.out.println("做了一份咖喱鸡盖饭");
	}

	/**
	 * after
	 */
	public void brush() {
		System.out.println("又要洗碗了");
	}

	public void eat() {
		cook();
		person.eat();
		brush();
	}

	public static void main(String[] args) {
		new PersonProxy(new Person()).eat();
	}
}

class Person {
	public void eat() {
		System.out.println("开始吃饭啦...");
	}
}