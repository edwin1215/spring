package com.edwin.spring.web.ioc.methodDI.replace;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

import com.edwin.spring.web.ioc.Car;

public class Boss2 implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		Car car = new Car();
		car.setBrand("奔驰S4");
		return car;
	}

}
