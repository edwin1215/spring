package com.edwin.spring.web.jvm.cloader;

import java.net.URL;
import java.security.PrivilegedAction;

/**
 * -XX:+TraceClassLoading
 * -XX:+TraceClassUnloading
 */
public class ClassLoaderFactory implements
		PrivilegedAction<StandardClassLoader> {

	ClassLoader paramClassLoader;
	URL[] paramArrayOfURL;
	public ClassLoaderFactory(ClassLoader paramClassLoader,
			URL[] paramArrayOfURL) {
		this.paramClassLoader = paramClassLoader;
		this.paramArrayOfURL = paramArrayOfURL;
	}

	@Override
	public StandardClassLoader run() {
		if (this.paramClassLoader == null) {
			return new StandardClassLoader(this.paramArrayOfURL);
		}
		return new StandardClassLoader(this.paramArrayOfURL);
	}

}
