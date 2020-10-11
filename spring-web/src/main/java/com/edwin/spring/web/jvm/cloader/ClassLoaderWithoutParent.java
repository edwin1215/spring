package com.edwin.spring.web.jvm.cloader;

import java.io.InputStream;

/**
 * <被破坏双亲委派模型>
 * -XX:+TraceClassLoading
 * -XX:+TraceClassUnloading
 *
 * @author caojunming
 *
 */
public class ClassLoaderWithoutParent {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		ClassLoader loader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name)
					throws ClassNotFoundException {
				try {
//					Class<?> aClass = loadClass(name, false);
//					if (aClass != null) {
//						return aClass;
//					}
					String fileName = name.substring(name.lastIndexOf('.') + 1)
							+ ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					}

					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (Exception e) {
					throw new ClassNotFoundException();
				}
			}
		};

		System.out.println(loader.getParent());
		Object obj = loader.loadClass(
				"com.edwin.spring.web.jvm.cloader.ClassLoaderWithoutParent")
				.newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof ClassLoaderWithoutParent);
	}
}
