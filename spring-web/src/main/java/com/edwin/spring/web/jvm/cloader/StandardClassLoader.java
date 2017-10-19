package com.edwin.spring.web.jvm.cloader;

import java.net.URL;
import java.net.URLClassLoader;

public class StandardClassLoader extends URLClassLoader {

	public StandardClassLoader(URL[] repositories) {
		super(repositories);
	}
}
