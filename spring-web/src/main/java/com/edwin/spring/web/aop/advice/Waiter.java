package com.edwin.spring.web.aop.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Waiter {

	public final static Logger LOGGER = LoggerFactory.getLogger(Waiter.class);

	public void greetTo(String name);

	public void serverTo(String name);
}
