package com.edwin.spring.web.jvm.passive;

import java.io.InputStream;

/**
 * 类加载器
 * 
 * @author caojunming
 *
 */
public class ClassLoaderTest {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		ClassLoader loader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name)
					throws ClassNotFoundException {
				try {
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

		Object obj = loader.loadClass(
				"com.edwin.spring.web.jvm.passive.ClassLoaderTest")
				.newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof ClassLoaderTest);
	}
}
