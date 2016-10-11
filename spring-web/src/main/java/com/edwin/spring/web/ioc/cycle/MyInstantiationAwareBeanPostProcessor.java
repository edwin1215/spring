package com.edwin.spring.web.ioc.cycle;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends
		InstantiationAwareBeanPostProcessorAdapter {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass,
			String beanName) throws BeansException {
		if ("car".equals(beanName)) {
			System.out
					.println("MyInstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation");
		}
		return null;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if ("car".equals(beanName)) {
			System.out
					.println("MyInstantiationAwareBeanPostProcessor.postProcessBeforeInitialization");
		}
		return bean;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs,
			PropertyDescriptor[] pds, Object bean, String beanName)
			throws BeansException {
		if ("car".equals(beanName)) {
			System.out
					.println("MyInstantiationAwareBeanPostProcessor.postProcessPropertyValues");
		}
		return pvs;
	}
}
