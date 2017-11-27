package com.edwin.spring.web.algorithm;

import java.util.Arrays;

/**
 * 归并排序
 * 
 * @author caojunming
 *
 */
public class MergeSort {

  public static void main(String[] args) {
    int[] arr = {2, 7, 8, 3, 1, 6, 9, 5, 4};
    MergeSort.sortRecursion(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));

    int[] arr2 = {1, 2, 2, 2, 4, 1, 2, 2, 4, 5};
    MergeSort.sortRecursion(arr2, 0, arr2.length - 1);
    System.out.println(Arrays.toString(arr2));
  }
  
  /**
   * 循环实现
   * 
   * @param arr
   * @param low
   * @param high
   */
  public static void sort(int[] arr, int low, int high) {
    int mid = low + (high - low) / 2;
    if (low < high) {
      sort(arr, low, mid);
      sort(arr, mid + 1, high);
      merge(arr, low, mid, high);
    }
  }

  /**
   * 递归实现
   * 
   * @param arr
   * @param low
   * @param high
   */
  public static void sortRecursion(int[] arr, int low, int high) {
    int mid = low + (high - low) / 2;
    if (low < high) {
      sort(arr, low, mid);
      sort(arr, mid + 1, high);
      merge(arr, low, mid, high);
    }
  }

  public static void merge(int[] arr, int low, int mid, int high) {
    int[] tempArr = new int[high - low + 1];
    int lp = low;
    int rp = mid + 1;
    int i = 0;
    while (lp <= mid && rp <= high) {
      if (arr[lp] < arr[rp]) {
        tempArr[i++] = arr[lp++];
      } else {
        tempArr[i++] = arr[rp++];
      }
    }

    while (lp <= mid) {
      tempArr[i++] = arr[lp++];
    }

    while (rp <= high) {
      tempArr[i++] = arr[rp++];
    }
    for (int k = 0; k < tempArr.length; k++) {
      arr[k + low] = tempArr[k];
    }
  }
}
