package com.edwin.spring.web.aop.advice;

public class NativeWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		LOGGER.info("greet to {}...", name);
	}

	@Override
	public void serverTo(String name) {
		LOGGER.info("serving {}...", name);
	}

}
