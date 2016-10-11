package com.edwin.spring.web.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.edwin.spring.web.aop.monitor.PerformanceMonitor;

/**
 * JDK动态代理
 * 
 * @author caojunming
 *
 */
public class PerformanceHandler implements InvocationHandler {

	private Object target;

	public PerformanceHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		PerformanceMonitor.begin(target.getClass().getName() + "."
				+ method.getName());
		// 真实的对象
		System.out.println("method:" + method);
		Object obj = method.invoke(target, args);
		PerformanceMonitor.end();
		return obj;
	}

}
