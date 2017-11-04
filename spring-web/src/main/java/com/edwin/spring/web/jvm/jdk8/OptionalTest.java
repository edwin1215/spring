package com.edwin.spring.web.jvm.jdk8;

import java.util.Optional;

/**
 * null判断
 * 
 * @author caojunming
 * @data 2017年11月4日 下午2:41:08
 */
public class OptionalTest {

	public static void main(String[] args) {
		Optional<String> fullName = Optional.ofNullable(null);
		System.out.println("Full Name is set? " + fullName.isPresent());
		System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
		System.out.println(fullName.map(s -> "Hey " + s + "!").orElse(
				"Hey Stranger!"));
	}
}
