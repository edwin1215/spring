package com.edwin.spring.web.algorithm;

import java.util.Arrays;

/**
 * 插入排序
 * 
 * @author caojunming
 * @data 2017年11月23日 下午12:27:39
 */
public class InsertionSort {

  public static void main(String[] args) {
    int[] arr = CommonUtil.getRandomArray(1000);
    int[] arr2 = arr.clone();
    sort(arr);
    System.out.println();
    sort2(arr2);
  }

  public static void sort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int temp = arr[i];
      int j = i - 1;
      for (; (j >= 0) && (temp < arr[j]); j--) {
          arr[j + 1] = arr[j];
      }
      arr[j + 1] = temp;
    }
    System.out.println("after: " + Arrays.toString(arr));
  }

  public static void sort2(int[] array) {
    int i, j;
    int temp;
    for (i = 1; i < array.length; i++) {
      temp = array[i];
      j = i - 1;

      // 与已排序的数逐一比较，大于temp时，该数后移
      while ((j >= 0) && (array[j] > temp)) // 当first=0，j循环到-1时，由于[[短路求值]]，不会运算array[-1]
      {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = temp; // 被排序数放到正确的位置

    }
    System.out.println("after: " + Arrays.toString(array));
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
