package com.edwin.spring.web.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 * 
 * @author caojunming
 * @data 2017年11月23日 下午3:18:18
 */
public class BubbleSort {

  public static void main(String[] args) {
    int[] arr = {12, 34, 45, 65, 23, 13, 48, 53, 11, 32, 12};
    sort(arr);
  }

  public static void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = arr.length - 1; j > i; j--) {
        if (arr[j] < arr[j - 1]) {
          arr[j] = arr[j] ^ arr[j - 1];
          arr[j - 1] = arr[j] ^ arr[j - 1];
          arr[j] = arr[j] ^ arr[j - 1];
        }
      }
    }
    Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
  }

  /**
   * in-place操作
   */
  public static void inPlace() {
    int a = 8;
    int b = 15;
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    System.out.println(a);
    System.out.println(b);
  }
}
