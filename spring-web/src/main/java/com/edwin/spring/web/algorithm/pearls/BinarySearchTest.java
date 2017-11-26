package com.edwin.spring.web.algorithm.pearls;

public class BinarySearchTest {

  public static void main(String[] args) {
    test();
  }

  public static void test() {
    String str = "abcdefgh";
    int size = str.length();
    int i = 3;
    String str1 = str.substring(0, i);
    String str2 = str.substring(i, size);
    System.out.println(str1 + " " + str2);
  }
}
