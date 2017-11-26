package com.edwin.spring.web.algorithm;

import java.util.Random;

public class CommonUtil {

  public static int[] getRandomArray(int length) {
    int[] arr = new int[length];
    Random r = new Random();
    // Arrays.stream(arr).map(a -> (a += r.nextInt(100)) > 50 ? a : a + 100)
    // .forEach(System.out::println);
    for (int i = 0; i < length; i++) {
      arr[i] = r.nextInt(100);
    }
    return arr;
  }

  public static int[] getRandomArray(int length, int max) {
    int[] arr = new int[length];
    Random r = new Random();
    for (int i = 0; i < length; i++) {
      arr[i] = r.nextInt(max);
    }
    return arr;
  }

  public static void bitSwap(int[] arr, int a, int b) {
    if (a == b) {
      return;
    }
    arr[a] = arr[a] ^ arr[b];
    arr[b] = arr[a] ^ arr[b];
    arr[a] = arr[a] ^ arr[b];
  }

  public static void tempSwap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }
}
