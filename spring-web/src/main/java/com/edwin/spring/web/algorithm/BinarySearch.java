package com.edwin.spring.web.algorithm;

/**
 * 二分查找算法
 * 
 * @author caojunming
 * @data 2017年11月13日 下午2:33:55
 */
public class BinarySearch {

  public static void main(String[] args) {
    int[] arr = {1, 3, 4, 5, 6, 8, 9, 10, 12, 15};
    System.out.println(getPosition(arr, 7));

  }

  public static int getPosition(int[] arr, int a) {

    int begin = 0;
    int end = arr.length - 1;

    while (begin <= end) {
      int pos = begin + (end - begin) / 2;
      System.out.println("----" + pos);
      if (arr[pos] > a) {
        end = pos - 1;
      } else if (arr[pos] < a) {
        begin = pos + 1;
      } else {
        return pos;
      }
    }
    return -1;
  }
}
