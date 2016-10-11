package com.edwin.spring.web.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean,
		DisposableBean {

	private String brand;

	private String color;

	private int maxSpeed;

	private BeanFactory beanFactory;

	private String beanName;

	public Car() {
		System.out.println("调用Car()构造方法.");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("调用setBrand()设置属性:" + brand);
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		System.out.println("调用setColor()设置属性:" + color);
		this.color = color;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		System.out.println("调用setMaxSpeed()设置属性:" + maxSpeed);
		this.maxSpeed = maxSpeed;
	}

	public void introduce() {
		System.out.println("brand:" + brand + ",color:" + color + ",maxSpeed:"
				+ maxSpeed);
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用BeanFactoryAware.setBeanFactory().");
		this.beanFactory = beanFactory;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String name) {
		System.out.println("调用BeanNameAware.setBeanName().");
		this.beanName = name;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("调用InitializingBean.afterPropertiesSet().");
	}

	public void destroy() throws Exception {
		System.out.println("调用DisposableBean.destroy().");
	}

	public void myInit() {
		System.out.println("调用init-method指定的myInit()方法, 将maxSpeed设置为240.");
		this.maxSpeed = 240;
	}

	public void myDestroy() {
		System.out.println("调用destroy-method指定的myDestroy()方法.");
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", maxSpeed="
				+ maxSpeed + ", beanName=" + beanName + "]";
	}
}
