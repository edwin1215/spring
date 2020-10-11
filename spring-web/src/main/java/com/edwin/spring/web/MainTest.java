package com.edwin.spring.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edwin.spring.web.ioc.methodDI.lookup.MagicBoss;
import com.edwin.spring.web.ioc.methodDI.replace.Boss1;

public class MainTest {

	public static ApplicationContext ac = null;

	static {
		ac = new ClassPathXmlApplicationContext("spring-application.xml");
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		Boss1 bean = ac.getBean("boss1", Boss1.class);
		System.out.println(bean.getCar().getBrand());
		System.out.println("..................................");
		MagicBoss bean2 = ac.getBean("magicBoss", MagicBoss.class);
		System.out.println(bean2.getCar());
		System.out.println("..................................");
	}
}
