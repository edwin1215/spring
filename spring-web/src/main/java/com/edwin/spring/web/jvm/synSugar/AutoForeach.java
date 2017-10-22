package com.edwin.spring.web.jvm.synSugar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 自动装箱、自动拆箱、Foreach
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
		List list = Arrays.asList(Integer.valueOf(1), Integer.valueOf(2),
				Integer.valueOf(3), Integer.valueOf(4));

		int sum = 0;
		for (Iterator it = list.iterator(); it.hasNext();) {
			int i = ((Integer) it.next()).intValue();
			sum += i;
		}
		System.out.println(sum);
	}
}
