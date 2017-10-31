package com.edwin.spring.web.jvm.tools;

/**
 * 
 * @author caojunming
 * @data 2017年10月29日 下午2:53:51
 */
public class HsdbTest {
	static TestA a = new TestA();
	TestA b = new TestA();

	@SuppressWarnings("unused")
	public void excute() {
		TestA c = new TestA();
	}

	public static void main(String[] args) {
		HsdbTest hsdbTest = new HsdbTest();
		hsdbTest.excute();
	}
}

class TestA {
	@SuppressWarnings("unused")
	private String s = "123";
}