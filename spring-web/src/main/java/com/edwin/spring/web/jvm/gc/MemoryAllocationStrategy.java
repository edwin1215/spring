package com.edwin.spring.web.jvm.gc;

/**
 * 内存分配策略
 * 
 * @author caojunming
 * @data 2017年11月4日 下午1:11:13
 */
public class MemoryAllocationStrategy {

	private static final int _1MB = 1024 * 1024;

	/**
	 * VM：-verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728
	 * -XX:+UseSerialGC
	 * -XX:+UseConcMarkSweepGC
	 */
	@SuppressWarnings("unused")
	public static void testAllocation() {
//		byte[] allocation1 = new byte[2 * _1MB];
//		byte[] allocation2 = new byte[2 * _1MB];
//		byte[] allocation3 = new byte[2 * _1MB];
//		byte[] allocation35 = new byte[2 * _1MB];
//		byte[] allocation4 = new byte[4 * _1MB];
		byte[] allocation1_1 = new byte[9 * _1MB];
//		try {
//			Thread.sleep(1000000000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(allocation1);
//		System.out.println(allocation2);
//		System.out.println(allocation3);
//		System.out.println(allocation4);
	}
	
	/**
	 * VM：-verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 */
	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		// allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[3 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}

	public static void main(String[] args) {
		 testAllocation();
//		testTenuringThreshold();
	}
}