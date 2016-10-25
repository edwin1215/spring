package com.edwin.spring.server.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Edwin on 2016/10/20.
 */
public class Provider {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-provider.xml" });
		context.start();
		System.in.read(); // 按任意键退出
	}
}
