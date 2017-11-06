package com.edwin.spring.web.jvm.cloader;

import java.lang.reflect.Method;

public class TestFanshe {

	public void print() {
		System.out.println("hahahah");
	}

	public static void main(String[] args) throws Exception {
		Method method = TestFanshe.class.getMethod("print", new Class<?>[] {});
		method.invoke(TestFanshe.class.newInstance(), new Object[] {});
	}
}
