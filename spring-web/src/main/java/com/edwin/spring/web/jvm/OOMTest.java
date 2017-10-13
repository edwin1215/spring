package com.edwin.spring.web.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * OOM
 * 
 * @author caojunming
 *
 */
public class OOMTest {

	private int stackLength = 1;

	public static void main(String[] args) {
		OOMTest o = new OOMTest();
		o.heapOom();
		// o.stackOom();
		// o.stackSof();
		// System.out.println(System.getProperties());
	}

	/**
	 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\test
	 * java.lang.OutOfMemoryError: Java heap space
	 */
	public void heapOom() {
		int counter = 1;
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			counter++;
			System.out.println(counter);
			list.add(new OOMObject());
		}
	}

	class OOMObject {

	}

	public void stackOom() {
		while (true) {

		}
	}

	public void doStop() {
		while (true) {

		}
	}

	/**
	 * java.lang.StackOverflowError
	 */
	public void stackSof() {
		stackLength++;
		System.out.println(stackLength);
		stackSof();
	}
}
