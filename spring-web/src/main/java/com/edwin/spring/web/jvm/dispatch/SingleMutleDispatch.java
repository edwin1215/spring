package com.edwin.spring.web.jvm.dispatch;

/**
 * 单分派、多分派
 * 
 * @author caojunming
 * @data 2017年11月5日 上午1:18:11
 */
public class SingleMutleDispatch {

	static class Car {
		public void speed() {
			System.out.println("Car speed");
		}
	}

	static class Ferrari extends Car {
		public void speed() {
			System.out.println("Ferrari speed");
		}
	}

	static class Lamborghini extends Car {
		public void speed() {
			System.out.println("Lamborghini speed");
		}
	}

	public static class Father {
		public void drive(Car Car) {
			Car.speed();
			System.out.println("Father drive Car");
		}

		public void drive(Ferrari ferrari) {
			System.out.println("Father drive Ferrari");
		}

		public void drive(Lamborghini lamborghini) {
			System.out.println("Father drive Lamborghini");
		}
	}

	public static class Son extends Father {
		@Override
		public void drive(Car Car) {
			System.out.println("Son drive Car");
		}

		@Override
		public void drive(Ferrari ferrari) {
			System.out.println("Son drive Ferrari");
		}

		@Override
		public void drive(Lamborghini lamborghini) {
			System.out.println("Son drive Lamborghini");
		}
	}

	public static void main(String[] args) {
		Father father = new Father();
		Father son = new Son();

		Ferrari ferrari = new Ferrari();
		Lamborghini lamborghini = new Lamborghini();

		father.drive(ferrari);
		son.drive(lamborghini);
		son.drive(new Car());
	}
}
