package com.edwin.spring.web.structure;

/**
 * 位图
 * 
 * @author caojunming
 * @data 2017年11月23日 上午11:00:37
 */
public class BitMap {

  public static int bm = 0;

  public static void set(int a) {
    bm |= (1 << a);
    System.out.println(Integer.toBinaryString(bm));
  }

  public static int get(int a) {
    return (bm & (1 << a)) > 0 ? 1 : 0;
  }

  public static void main(String[] args) {
    check();
  }

  public static void check() {
    set(8);
    set(4);
    set(128);
    System.out.println("-------begin-------");
    for (int i = 0; i < 1024; i++) {
      int j = get(i + 1);
      if (j > 0) {
        System.out.println(i + 1);
      }
    }
    System.out.println("-------end-------");
  }
}
