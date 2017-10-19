package com.edwin.spring.web.jvm.cloader;

import java.io.InputStream;

import com.edwin.spring.web.structure.LinkStructure;

/**
 * 双亲委派机制下的自定义类加载器
 * 
 * @author caojunming
 *
 */
public class ClassLoaderWithParent {

	public static void main(String[] args) throws Exception {
		System.out.println(LinkStructure.class.hashCode());
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
				"com.edwin.spring.web.structure.LinkStructure").newInstance();
		System.out.println(obj.getClass().hashCode());
		System.out.println(LinkStructure.class.hashCode());
		System.out.println(obj.getClass().equals(LinkStructure.class));
		System.out.println(obj instanceof LinkStructure);
	}
}
