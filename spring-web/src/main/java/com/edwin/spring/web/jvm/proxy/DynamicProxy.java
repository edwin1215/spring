package com.edwin.spring.web.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理模式
 * 
 * @author caojunming
 * @data 2017年10月21日 下午7:20:06
 */
public class DynamicProxy implements InvocationHandler {

	Object target;

	public void before() {
		System.out.println("welcome to you!");
	}

	public void after() {
		System.out.println("goodbye!");
	}

	public Object proxy(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object invoke = method.invoke(target, args);
		after();
		return invoke;
	}

	public static void main(String[] args) {
		IHello proxy = (IHello) new DynamicProxy().proxy(new Hello());
		proxy.sayHi();
	}

}

interface IHello {
	void sayHi();
}

class Hello implements IHello {
	@Override
	public void sayHi() {
		System.out.println("hello world");
	}
}