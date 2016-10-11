package com.edwin.spring.web.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.edwin.spring.web.aop.monitor.PerformanceMonitor;

/**
 * cglib字节码增强
 * 
 * @author caojunming
 *
 */
public class CglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class<?> clazz) {
		// 设置需要创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		// 通过字节码技术动态创建子类实例
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		PerformanceMonitor.begin(obj.getClass().getName() + ":"
				+ method.getName());
		// 真实对象的方法调用
		System.err.println("proxy:" + proxy);
		Object result = proxy.invokeSuper(obj, args);
		PerformanceMonitor.end();
		return result;
	}
}
