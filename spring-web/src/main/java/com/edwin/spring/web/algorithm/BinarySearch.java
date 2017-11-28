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
    int[] arrRep = {1, 3, 4, 4, 5, 5, 5, 5, 5, 6, 6, 8, 9, 12};
    getRangePosition(arrRep, 4);

  }

  /**
   * 无重复数据二分查找
   * 
   * @param arr
   * @param a
   * @return
   */
  public static int getPosition(int[] arr, int a) {
    int lp = 0;
    int rp = arr.length - 1;
    while (lp <= rp) {
      int mid = lp + (rp - lp) / 2;
      System.out.println("----" + mid);
      if (arr[mid] > a) {
        rp = mid - 1;
      } else if (arr[mid] < a) {
        lp = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  /**
   * 范围二分查找
   * 
   * @param arr
   * @param a
   */
  public static void getRangePosition(int[] arr, int a) {
    // 整体范围指针
    int lp = 0;
    int rp = arr.length - 1;
    // 相同值的范围指针
    int low = lp;
    int high = rp;

    while (lp <= rp) {
      int mid = lp + (rp - lp) / 2;
      System.out.println("----" + mid);
      if (arr[mid] > a) {
        rp = mid - 1;
      } else if (arr[mid] < a) {
        lp = mid + 1;
      } else {
        low = mid;
        high = mid;
        while (lp < low) {
          int lmid = lp + (low - lp) / 2;
          if (arr[lmid] < a) {
            lp = lmid + 1;
          } else if (arr[lmid] == a) {
            low = lmid;
          }
        }
        while (rp > high) {
          int rmid = high + (rp - high) / 2 + 1;
          if (arr[rmid] > a) {
            rp = rmid - 1;
          } else if (arr[rmid] == a) {
            high = rmid;
          }
        }
        break;
      }
    }
    System.out.println("low:" + low + ", high:" + high);
  }
}
