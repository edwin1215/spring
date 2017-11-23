package com.edwin.spring.web.algorithm;

/**
 * 时间复杂度
 * 
 * @author caojunming
 * @data 2017年11月23日 下午5:49:10
 */
public class TimeComplexity {

  public static void main(String[] args) {
    System.out.println(Math.sqrt(9));
    // System.out.println(one(3));
  }

  public static int one(int n) {
    double pow = Math.pow(n, 2);
    return (int) pow * 2 + n + 1;
  }

  public static int two(int n) {
    double pow = Math.sqrt(n);
    return (int) pow * 2 + n + 1;
  }
}
