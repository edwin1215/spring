package com.edwin.spring.web.jvm.dispatch;

/**
 * 动态分派（Override）
 * 
 * @author caojunming
 * @data 2017年10月20日 下午11:12:20
 */
public class DynamicDispatch {

	public static void main(String[] args) {
		People man = new Man();
		People woman = new Woman();
		man.say();
		woman.say();
	}

	static class People {
		public void say() {
			System.out.println("i am a people");
		}
	}

	static class Man extends People {
		@Override
		public void say() {
			System.out.println("i am a man");
		}
	}

	static class Woman extends People {
		@Override
		public void say() {
			System.out.println("i am a woman");
		}
	}
}
