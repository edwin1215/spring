package com.edwin.spring.web.aop.advice.before;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * 前置增强
 * 
 * @author caojunming
 *
 */
public class ServerBeforeAdvice implements MethodBeforeAdvice {
	public final static Logger LOGGER = LoggerFactory
			.getLogger(ServerBeforeAdvice.class);

	/**
	 * 在调用方法前执行
	 */
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		String name = (String) args[0];
		LOGGER.info("How are you! Mr.{}", name);
	}
}
