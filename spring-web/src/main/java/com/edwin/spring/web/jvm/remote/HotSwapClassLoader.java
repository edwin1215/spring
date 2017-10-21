package com.edwin.spring.web.jvm.remote;

/**
 * 热替换
 * 
 * @author caojunming
 * @data 2017年10月22日 上午12:51:24
 */
public class HotSwapClassLoader extends ClassLoader {

	public HotSwapClassLoader() {
		super(HotSwapClassLoader.class.getClassLoader());
	}

	public Class<?> loadByte(byte[] classByte) {
		return defineClass(null, classByte, 0, classByte.length);
	}
}
