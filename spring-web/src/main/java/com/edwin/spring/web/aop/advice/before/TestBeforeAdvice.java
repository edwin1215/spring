package com.edwin.spring.web.aop.advice.before;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import com.edwin.spring.web.aop.advice.NativeWaiter;
import com.edwin.spring.web.aop.advice.Waiter;

/**
 * 前置增强测试
 * 
 * @author caojunming
 *
 */
public class TestBeforeAdvice {

	public static void main(String[] args) {
		Waiter waiter = new NativeWaiter();
		BeforeAdvice advice = new ServerBeforeAdvice();
		// spring提供的代理工厂
		ProxyFactory pf = new ProxyFactory();
		// 设置代理目标
		pf.setTarget(waiter);
		// 为代理目标添加增强
		pf.addAdvice(advice);
		// 针对接口进行代理，使用jdk动态代理
		// pf.setInterfaces(waiter.getClass().getInterfaces());
		// 启动代理优化，使用cglib代理方式
		// pf.setOptimize(true);
		// 生成代理实例
		Waiter proxy = (Waiter) pf.getProxy();
		proxy.greetTo("John");
		proxy.serverTo("Tom");
	}
}
