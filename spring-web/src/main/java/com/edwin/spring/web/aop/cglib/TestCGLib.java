package com.edwin.spring.web.aop.cglib;

import com.edwin.spring.web.aop.ForumServiceImpl;

/**
 * CGLib测试类
 * 
 * @author caojunming
 *
 */
public class TestCGLib {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		ForumServiceImpl service = (ForumServiceImpl) proxy
				.getProxy(ForumServiceImpl.class);
		// ForumServiceImpl类的子类
		System.err.println("service:" + service.getClass());
		service.removeTopic(100);
		service.removeForum(200);
	}
}
