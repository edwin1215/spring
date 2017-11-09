package com.edwin.spring.web.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
	}
}
