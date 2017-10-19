package com.edwin.spring.web.structure;

import java.io.InputStream;

/**
 * 链表结构
 * 
 * @author caojunming
 *
 */
public class LinkStructure {

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
