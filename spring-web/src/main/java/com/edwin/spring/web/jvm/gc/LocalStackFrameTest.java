package com.edwin.spring.web.jvm.gc;

/**
 * 局部变量表GC
 * <-verbose:gc>
 * <-XX:+PrintGCDetails>
 * 
 * @author caojunming
 */
public class LocalStackFrameTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		{
			byte[] placeholder = new byte[64 * 1024 * 1024];
			// placeholder = null;
		}
		int i = 0;
		System.gc();
	}
}
