package com.edwin.spring.web.aop.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 性能监视类
 * 
 * @author caojunming
 *
 */
public class PerformanceMonitor {

	public final static Logger LOGGER = LoggerFactory
			.getLogger(PerformanceMonitor.class);
	private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();

	public static void begin(String methodName) {
		LOGGER.info("begin monitor...");
		MethodPerformance mp = new MethodPerformance(methodName);
		performanceRecord.set(mp);
	}

	public static void end() {
		LOGGER.info("end monitor...");
		MethodPerformance mp = performanceRecord.get();
		mp.printPerformance();
	}
}
