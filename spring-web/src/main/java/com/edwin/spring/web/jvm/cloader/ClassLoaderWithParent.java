package com.edwin.spring.web.jvm.cloader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 双亲委派机制下的自定义类加载器
 * 
 * @author caojunming
 *
 */
public class ClassLoaderWithParent {

	public static void print() {
		System.out.println("1234567");
	}

	public static void main(String[] args) throws Exception {
		ClassLoader loader = new ClassLoader() {
			@Override
			protected Class<?> findClass(String name)
					throws ClassNotFoundException {
				try {
					System.out.println("---:" + name);
					String fileName = name.substring(name.lastIndexOf(".") + 1)
							+ ".class";
					InputStream is = this.getClass().getResourceAsStream(
							fileName);
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (Exception e) {
					throw new ClassNotFoundException(name);
				}
			}
		};
		Object obj = loader.loadClass(
				"com.edwin.spring.web.jvm.cloader.ClassLoaderWithParent")
				.newInstance();
		System.out.println(obj.getClass().hashCode());
		System.out.println(ClassLoaderWithParent.class.hashCode());
		System.out.println(obj.getClass().equals(ClassLoaderWithParent.class));
		System.out.println(obj instanceof ClassLoaderWithParent);

		Class<?> loadClass = loader
				.loadClass("com.edwin.spring.web.jvm.cloader.ClassLoaderWithParent");
		Method method = loadClass.getMethod("print", new Class<?>[] {});
		method.invoke(loadClass.newInstance(), new Object[] {});
	}
}

class MyClassLoader extends URLClassLoader {
	public MyClassLoader(URL[] repositories) {
		super(repositories);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			System.out.println("---:" + name);
			String fileName = name.substring(name.lastIndexOf(".") + 1)
					+ ".class";
			InputStream is = this.getClass().getResourceAsStream(fileName);
			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(name, b, 0, b.length);
		} catch (Exception e) {
			throw new ClassNotFoundException(name);
		}
	}
}