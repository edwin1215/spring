package com.edwin.spring.web.jvm.cloader;

/**
 * classloader资源访问路径
 * 
 * @author caojunming
 *
 */
public class ClassLoaderPath {

	/**
	 * -XX:+TraceClassLoading
	 * -XX:+TraceClassUnloading
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ClassLoader contextClassLoader = Thread.currentThread()
				.getContextClassLoader();
		System.out.println(contextClassLoader.getResource(".").getPath());
		System.out.println(ClassLoaderPath.class.getClassLoader()
				.getResource(".").getPath());
		System.out.println(ClassLoaderPath.class.getResource("").getPath());
	}
}
