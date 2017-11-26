package com.edwin.spring.web.algorithm.pearls.act2;

import java.util.Arrays;

/**
 * 
 * @subject 将一个n元一维数组向量向左旋转i个位置，如：n=8,i=3,str=abcdefgh->str=defghabc
 * @requirement 使用至多十个额外字节的存储空间，时间复杂度O(n)
 * @author caojunming
 * @data 2017年11月26日 下午6:48:49
 */
public class QuestionB {

  public static void main(String[] args) {
    char[] arr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    answer2(arr, 4);

    System.out.println(gcd(13, 6));
  }

  /**
   * 临时数组
   * 
   * @param arr
   * @param p
   */
  public static void answer1(char[] arr, int p) {
    if (arr == null || arr.length < 2) {
      return;
    }
    char[] temp = new char[p];
    for (int i = 0; i < p; i++) {
      temp[i] = arr[i];
    }
    for (int i = p; i < arr.length; i++) {
      arr[i - p] = arr[i];
    }
    for (int i = arr.length - p; i < arr.length; i++) {
      arr[i] = temp[i + p - arr.length];
    }
    System.out.println(Arrays.toString(arr));
  }

  /**
   * 
   * @param arr
   * @param p
   */
  public static void answer2(char[] arr, int p) {
    if (arr == null || arr.length < 2) {
      return;
    }
    // n为字符总长度
    int n = arr.length;
    // 需要最大公约数次置换
    int numbers = gcd(n, p);

    // 执行n和i的最大公约数次置换
    for (int j = 0; j < numbers; j++) {
      // 记录每一次替换所需步数
      int steps = 0;
      // 1. 每次置换开始的数组索引缓存到t
      char t = arr[j];
      // 2. 需要替换的数组游标
      int index = j;
      // 5. 需要从置换开始的数组索引取值时，循环结束
      while ((index + p) % n != j) {
        // 3. 被其后面的第i个值替换，需要对长度取余
        arr[index % n] = arr[(index + p) % n];
        // 4.游标后移i个位置
        index += p;
        steps++;
      }
      // 6. 需要从置换开始的数组索引取值时，将t赋值给它
      arr[index % n] = t;
      System.out.println("第" + (j + 1) + "次置换,移动次数为:" + steps);
    }

    System.out.println(Arrays.toString(arr));
  }

  /**
   * 欧几里得算法，求最大公约数
   * 
   * @param a
   * @param b
   * @return
   */
  public static int gcd(int a, int b) {
    if (b != 0) {
      return gcd(b, a % b);
    }
    return a;
  }
}
