package com.edwin.spring.web.jvm.dispatch;

/**
 * 单分派、多分派
 * 
 * @author caojunming
 * @data 2017年11月5日 上午1:18:11
 */
public class SingleMutleDispatch {

	static class Ferrari {
		public String name = "Ferrari";
	}

	static class Lamborghini {
		public String name = "Lamborghini";
	}

	public static class Father {
		public void drive(Ferrari ferrari) {
			System.out.println("Father drive " + ferrari.name);
		}

		public void drive(Lamborghini lamborghini) {
			System.out.println("Father drive " + lamborghini.name);
		}
	}

	public static class Son extends Father {
		public void drive(Ferrari ferrari) {
			System.out.println("Son drive " + ferrari.name);
		}

		public void drive(Lamborghini lamborghini) {
			System.out.println("Son drive " + lamborghini.name);
		}
	}

	public static void main(String[] args) {
		Father father = new Father();
		Father son = new Son();
		father.drive(new Ferrari());
		son.drive(new Lamborghini());
	}
}
