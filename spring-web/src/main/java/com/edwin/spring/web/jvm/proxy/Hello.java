package com.edwin.spring.web.jvm.proxy;

public class Hello implements IHello {

	@Override
	public void sayHi() {
		System.out.println("hello world");

	}

}
