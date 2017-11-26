package com.edwin.spring.web.algorithm;

import java.util.Arrays;

/**
 * 希尔排序
 * 
 * @author caojunming
 * @data 2017年11月24日 下午3:02:23
 */
public class ShellSort {

  public static void main(String[] args) {
    // int[] arr = {12, 34, 45, 65, 23, 13, 48, 53, 11, 32, 12, 38, 49, 89, 9};
    // Arrays.sort(arr);
    int[] randomArray = CommonUtil.getRandomArray(20);
    System.out.println("befor: " + Arrays.toString(randomArray));
    sort(randomArray);
  }

  /**
   * 希尔增量序列，时间复杂度O(n^2)
   * 
   * @param arr
   */
  public static void sort(int[] arr) {
    int size = arr.length;
    int temp;
    for (int gap = size / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < size; i++) {
        temp = arr[i];
        int a = i - gap;
        while (a >= 0 && temp < arr[a]) {
          arr[a + gap] = arr[a];
          a -= gap;
        }
        arr[a + gap] = temp;
      }
    }
    System.out.println("after: " + Arrays.toString(arr));
  }
}
