package com.edwin.spring.web.jvm.dispatch;

import java.io.Serializable;

public class OverloadTest {

	public static void say(Object o) {
		System.out.println("hello Object");
	}

	public static void say(Serializable s) {
		System.out.println("hello Serializable");
	}

	public static void say(int i) {
		System.out.println("hello int");
	}

	public static void say(long l) {
		System.out.println("hello long");
	}

	public static void say(Character c) {
		System.out.println("hello Character");
	}

	public static void say(char c) {
		System.out.println("hello char");
	}

	public static void say(Integer i) {
		System.out.println("hello Integer");
	}

	public static void say(char... c) {
		System.out.println("hello char...");
	}

	public static void say(int... c) {
		System.out.println("hello int...");
	}

	public static void say(long... c) {
		System.out.println("hello long...");
	}

	public static void main(String[] args) {
		say('a');
	}
}
