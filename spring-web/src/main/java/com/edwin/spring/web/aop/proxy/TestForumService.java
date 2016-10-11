package com.edwin.spring.web.aop.proxy;

import java.lang.reflect.Proxy;

import com.edwin.spring.web.aop.ForumService;
import com.edwin.spring.web.aop.ForumServiceImpl;

/**
 * 测试类
 * 
 * @author caojunming
 *
 */
public class TestForumService {

	public static void main(String[] args) {
		ForumService target = new ForumServiceImpl();
		PerformanceHandler handler = new PerformanceHandler(target);
		ForumService proxy = (ForumService) Proxy.newProxyInstance(target
				.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);
		// 代理对象
		System.out.println(proxy.getClass().getName());
		proxy.removeTopic(1012);
		proxy.removeForum(10);
	}
}
