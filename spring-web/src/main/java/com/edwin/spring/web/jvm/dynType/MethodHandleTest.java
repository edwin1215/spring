package com.edwin.spring.web.jvm.dynType;

import com.edwin.spring.web.tools.PrintUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Method Handle基础用法
 * 
 * @author caojunming
 * @data 2017年10月21日 上午2:06:34
 */
public class MethodHandleTest {

	public static void main(String[] args) throws Throwable {

		MethodHandleTest test = new MethodHandleTest();
		Object obj = System.currentTimeMillis() % 2 == 0 ? System.out
				: new ClassA();
		test.getPrintlnMethodHandle(obj).invokeExact("hahahaha");
	}

	private MethodHandle getPrintlnMethodHandle(Object obj)
			throws Throwable {
		MethodType mType = MethodType.methodType(void.class, String.class);
		return MethodHandles.lookup()
				.findVirtual(obj.getClass(), "println", mType).bindTo(obj);
	}
}

class ClassA {
	public void println(String str) {
		PrintUtils.sys("ClassA:" + str);
	}
}