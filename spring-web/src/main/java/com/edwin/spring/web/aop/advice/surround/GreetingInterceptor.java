package com.edwin.spring.web.aop.advice.surround;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * @author caojunming
 *
 */
public class GreetingInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		String name = (String) args[0];
		System.out.println(name);
		return null;
	}

}
