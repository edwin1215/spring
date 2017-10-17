package com.edwin.spring.web.structure;

import java.util.HashMap;

public class HashStructure {

	public static void main(String[] args) {
		testNullHash();
	}

	public static void testNullHash() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(null, "123");
		System.out.println(map.get(null));
	}
}
