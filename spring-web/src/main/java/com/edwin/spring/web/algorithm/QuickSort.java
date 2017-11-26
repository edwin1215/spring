package com.edwin.spring.web.algorithm;

import java.util.Arrays;



/**
 * 快排
 * 
 * @author caojunming
 *
 */
public class QuickSort {

  public static void main(String[] args) {
    int[] randomArray = CommonUtil.getRandomArray(20, 100);
    System.out.println("start");
    System.out.println("before: " + Arrays.toString(randomArray));
    long sTime = System.currentTimeMillis();
    System.out.println(sTime);
    qsort3(randomArray, 0, randomArray.length - 1);
    long eTime = System.currentTimeMillis();
    System.out.println(eTime);
    System.out.println("cost:" + (eTime - sTime));
    System.out.println("after: " + Arrays.toString(randomArray));
  }

  /**
   * 以第一个元素为划分点，依次向右查找
   * 
   * @param arr
   * @param low
   * @param high
   */
  public static void qsort1(int[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    int m = low;
    int t = arr[low];
    for (int i = low + 1; i <= high; i++) {
      if (arr[i] < t) {
        if (++m != i && m <= high) {
          // System.out.println("first:" + arr[low] + "," + m + "  " + i);
          CommonUtil.bitSwap(arr, m, i);
        }
      }
    }
    if (low != m) {
      CommonUtil.bitSwap(arr, low, m);
    }
    qsort1(arr, low, m - 1);
    qsort1(arr, m + 1, high);
    // System.out.println("after: " + Arrays.toString(arr));
  }

  /**
   * 以第一个元素为划分点，依次向左查找
   * 
   * @param arr
   * @param low
   * @param high
   */
  public static void qsort2(int[] arr, int low, int high) {

  }

  /**
   * 以第一个元素为划分点，双向查找
   * 
   * @param arr
   * @param low
   * @param high
   */
  public static void qsort3(int[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    int lp = low;
    int rp = high + 1;
    int t = arr[low];

    while (true) {
      do {
        lp++;
      } while (lp <= high && arr[lp] < t);

      do {
        rp--;
      } while (arr[rp] > t);
      if (lp > rp) {
        break;
      }
      CommonUtil.bitSwap(arr, lp, rp);
    }

    CommonUtil.bitSwap(arr, low, rp);
    qsort3(arr, low, rp - 1);
    qsort3(arr, rp + 1, high);
    System.out.println("after: " + Arrays.toString(arr));
  }

  /**
   * 随机元素作为划分点，双向查找，增加cutoff，如果排序元素少于cutoff直接退出当前，最后通过插入排序
   * 
   * @param arr
   * @param low
   * @param high
   */
  public static void qsort4(int[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    int lp = low;
    int rp = high + 1;
    int t = arr[low];

    while (true) {
      do {
        lp++;
      } while (lp <= high && arr[lp] < t);

      do {
        rp--;
      } while (arr[rp] > t);
      if (lp > rp) {
        break;
      }
      CommonUtil.bitSwap(arr, lp, rp);
    }

    CommonUtil.bitSwap(arr, low, rp);
    qsort3(arr, low, rp - 1);
    qsort3(arr, rp + 1, high);
    System.out.println("after: " + Arrays.toString(arr));
  }
}
