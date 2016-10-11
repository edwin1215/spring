package com.edwin.spring.web.ioc.methodDI.replace;

import com.edwin.spring.web.ioc.Car;

public class Boss1 {

	public Car getCar() {
		Car car = new Car();
		car.setBrand("宝马z4");
		return car;
	}
}
