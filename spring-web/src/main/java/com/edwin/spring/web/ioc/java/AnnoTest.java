package com.edwin.spring.web.ioc.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.edwin.spring.web.ioc.java.conf.DaoConfig;
import com.edwin.spring.web.ioc.java.conf.ServiceConfig;
import com.edwin.spring.web.ioc.java.pojo.LoginService;

public class AnnoTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// 两种基于java类的配置信息启动spring容器
		// ①
		// ApplicationContext ac = new AnnotationConfigApplicationContext(
		// new Class[] { ServiceConfig.class, DaoConfig.class });
		// LoginService bean = ac.getBean(LoginService.class);
		// System.out.println(bean);

		// ②
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(ServiceConfig.class);
		ac.register(DaoConfig.class);
		ac.refresh();
		LoginService bean = ac.getBean(LoginService.class);
		System.out.println(bean);
	}
}
