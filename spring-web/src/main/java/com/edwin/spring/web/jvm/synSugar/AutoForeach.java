package com.edwin.spring.web.jvm.synSugar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 自动装箱、自动拆箱、Foreach、变长参数
 * 
 * @author caojunming
 * @data 2017年10月22日 上午9:55:51
 */
public class AutoForeach {

	/**
	 * 编译前
	 */
	public void beforCompile() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		int sum = 0;
		for (int i : list) {
			sum += i;
		}
		System.out.println(sum);
	}

	/**
	 * 编译后
	 */
	@SuppressWarnings("rawtypes")
	public void afterCompile() {
		List list = Arrays.asList(new Integer[] {
				Integer.valueOf(1),
				Integer.valueOf(2), 
				Integer.valueOf(3), 
				Integer.valueOf(4) });

		int sum = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			int i = ((Integer) it.next()).intValue();
			sum += i;
		}
		System.out.println(sum);
	}

	/**
	 * 自动装、拆箱的陷阱
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Integer g = 300;
		Integer h = 21;
		Long j = 3L;
		Long k = 3L;
		System.out.println(e == f); // 【false】包装类“==”运算和其他对象一样，判断地址，是否是同一个对象
		System.out.println(c == d); // 【true】Integer有缓存,[-128,127]范围内的数都是从缓存里取同一个对象
		System.out.println(e == (g + h)); // 【true】 包装类“==”运算在遇到“算术运算符”的情况下会自动拆箱
		System.out.println(c.equals(a + b));// 【true】只要是同一类型的包装类，就可以正常比较
		System.out.println(j == (a + b));
		System.out.println(j.equals(a + b));// equals方法，首先判断是否属于当前封住类
		System.out.println(j == k);// Long也有缓存，同Integer
	}
}
