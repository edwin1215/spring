package com.edwin.spring.web.jvm.cloader;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * -XX:+TraceClassLoading
 * -XX:+TraceClassUnloading
 */
public class CreateClassLoader {

	public static void main(String[] args) {
		AccessController.doPrivileged(new PrivilegedAction<StandardClassLoader>() {
					@Override
					public StandardClassLoader run() {
						return null;
					}
				});
	}
}
