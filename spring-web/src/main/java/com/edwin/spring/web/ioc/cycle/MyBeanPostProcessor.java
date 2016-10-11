package com.edwin.spring.web.ioc.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.edwin.spring.web.ioc.Car;

public class MyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if ("car".equals(beanName)) {
			Car car = (Car) bean;
			if (isNull(car.getColor())) {
				System.out
						.println("调用MyBeanPostProcessor.postProcessBeforeInitialization()");
				car.setColor("black");
			}
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if ("car".equals(beanName)) {
			Car car = (Car) bean;
			if (car.getMaxSpeed() >= 200) {
				System.out
						.println("调用MyBeanPostProcessor.postProcessAfterInitialization()");
				car.setMaxSpeed(200);
			}
		}
		return bean;
	}

	private boolean isNull(String str) {
		return str == null || "".equals(str);
	}
}
