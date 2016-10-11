package com.edwin.spring.web.aop.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 性能计算类
 * 
 * @author caojunming
 *
 */
public class MethodPerformance {

	public final static Logger LOGGER = LoggerFactory
			.getLogger(MethodPerformance.class);
	private long begin;
	private long end;
	private String serviceMethod;

	public MethodPerformance(String serviceMethod) {
		this.serviceMethod = serviceMethod;
		this.begin = System.currentTimeMillis();
	}

	public void printPerformance() {
		end = System.currentTimeMillis();
		long elapse = end - begin;
		LOGGER.info("{}执行{}毫秒.", serviceMethod, elapse);
	}
}
