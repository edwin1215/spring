package com.edwin.spring.web.jvm.proxy;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 自定义classLoader，实现动态代理
 * 
 * @author caojunming
 *
 */
public class DynamicProxyTest {

	public static void main(String[] args) throws Exception {

		// DynamicProxy newInstance = DynamicProxy.class.newInstance();
		// IHello proxy = (IHello) newInstance.proxy(new Hello());
		// proxy.sayHi();

		proxy();
	}

	public static void proxy() throws Exception {
		MyClassLoader myClassLoader = new MyClassLoader();
		Class<?> proxyClass = myClassLoader
				.loadClass("com.edwin.spring.web.jvm.proxy.DynamicProxy");
		
		// myClassLoader.loadClass("com.edwin.spring.web.jvm.proxy.IHello");
		Class<?> helloClass = myClassLoader
				.loadClass("com.edwin.spring.web.jvm.proxy.Hello");
		
		Method method = proxyClass.getMethod("proxy", new Class<?>[] {
				Object.class, ClassLoader.class });
		Object helloProxyClass = method.invoke(proxyClass.newInstance(),
				helloClass.newInstance(), myClassLoader);
		
		Method method2 = helloProxyClass.getClass()
				.getMethod("sayHi", new Class<?>[] {});
		method2.invoke(helloProxyClass, new Object[] {});
		
		// IHello proxy = (IHello) newInstance.proxy(new Hello());
		// proxy.sayHi();
	}
}

class MyClassLoader extends ClassLoader {
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			String fileName = name.substring(name.lastIndexOf('.') + 1)
					+ ".class";
			InputStream is = getClass().getResourceAsStream(fileName);
			if (is == null) {
				return super.loadClass(name);
			}

			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(name, b, 0, b.length);
		} catch (Exception e) {
			throw new ClassNotFoundException();
		}
	}
}
