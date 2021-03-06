package com.edwin.spring.web.jvm;

/**
 * 对象逃逸 
 * 1.对象可以在GC时自我拯救 
 * 2.自救的机会只有一次，因为finalize方法在整个对象的生命周期中，只会被执行一次
 * 
 * @author Edwin
 *
 */
public class EscapeGC {

	public static EscapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("I am still Alive");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize excute ...");
		SAVE_HOOK = this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new EscapeGC();
		deleteObject();// 第一次GC对象成功逃逸
		deleteObject();// 第二次GC对象无法逃逸，因为finalize方法在整个对象的生命周期中，只会被执行一次
	}

	public static void deleteObject() throws InterruptedException {
		SAVE_HOOK = null;
		System.gc();
		// 因为finalize方法优先级很低，所以暂停0.5秒以等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("Object is Dead ...");
		}
	}
}
