package com.edwin.spring.web.ioc.cycle;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.edwin.spring.web.ioc.Car;

@SuppressWarnings("deprecation")
public class BeanLifeCycle {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("spring-application.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		((ConfigurableBeanFactory) factory)
				.addBeanPostProcessor(new MyBeanPostProcessor());
		((ConfigurableBeanFactory) factory)
				.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		Car car1 = (Car) factory.getBean("car");
		car1.introduce();
		car1.setColor("red");

		// Car car2 = (Car) factory.getBean("car");

		// System.out.println("car1 == car2:" + (car1 == car2));

		((XmlBeanFactory) factory).destroySingletons();
	}
}
