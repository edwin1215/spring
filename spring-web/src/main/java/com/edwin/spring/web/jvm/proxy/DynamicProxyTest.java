package com.edwin.spring.web.jvm.proxy;

import java.lang.reflect.Method;

public class DynamicProxyTest {

	public static void main(String[] args) throws Exception {

		DynamicProxy newInstance = DynamicProxy.class.newInstance();
		IHello proxy = (IHello) newInstance.proxy(new Hello());
		proxy.sayHi();

		proxy();
	}

	public static void proxy() throws Exception {
		MyClassLoader myClassLoader = new MyClassLoader();
		Class<?> proxyClass = myClassLoader
				.loadClass("com.edwin.spring.web.jvm.proxy.DynamicProxy");
		
		myClassLoader.loadClass("com.edwin.spring.web.jvm.proxy.IHello");
		Class<?> helloClass = myClassLoader.loadClass("com.edwin.spring.web.jvm.proxy.Hello");
		
		Method method = proxyClass.getMethod("proxy", Object.class);
		Object invoke = method.invoke(proxyClass.newInstance(),
				helloClass.newInstance());
		
		
		// IHello proxy = (IHello) newInstance.proxy(new Hello());
		// proxy.sayHi();
	}
}
