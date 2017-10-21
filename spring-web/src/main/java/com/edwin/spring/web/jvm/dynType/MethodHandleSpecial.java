package com.edwin.spring.web.jvm.dynType;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * MethodHandle.findSpecial
 * 
 * @author caojunming
 * @data 2017年10月21日 下午4:31:57
 */
public class MethodHandleSpecial {

	class GrandFather {
		void say() {
			System.out.println("i am grandfather");
		}
	}

	class Father extends GrandFather {
		void say() {
			System.out.println("i am father");
		}
	}

	class Son extends Father {
		void say() {
			try {
				MethodType mType = MethodType.methodType(void.class);
				MethodHandle mHandle = MethodHandles.lookup().findSpecial(
						GrandFather.class, "say", mType, getClass());
				// mHandle.bindTo(this);
				mHandle.invokeExact(this);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		(new MethodHandleSpecial().new Son()).say();
	}
}
