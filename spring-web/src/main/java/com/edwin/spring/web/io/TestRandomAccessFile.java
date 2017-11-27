package com.edwin.spring.web.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TestRandomAccessFile {
  public static void main(String[] args) throws IOException {
    String timeStr = String.valueOf(System.currentTimeMillis());
    System.out.println(timeStr);
    System.out.println(timeStr.substring(timeStr.length() - 6, timeStr.length()));
  }

  public static void deal() throws IOException {
    RandomAccessFile rf = new RandomAccessFile("D:/edwin/jvm/tmp/rtest.dat", "rw");
    for (int i = 0; i < 10; i++) {
      // 写入基本类型double数据
      rf.writeDouble(i * 1.414);
    }
    rf.close();
    rf = new RandomAccessFile("D:/edwin/jvm/tmp/rtest.dat", "rw");
    // 直接将文件指针移到第5个double数据后面
    rf.seek(5 * 8);
    // 覆盖第6个double数据
    rf.writeDouble(47.0001);
    rf.close();
    rf = new RandomAccessFile("D:/edwin/jvm/tmp/rtest.dat", "r");
    for (int i = 0; i < 10; i++) {
      System.out.println("Value" + i + ": " + rf.readDouble());
    }
    rf.close();
  }
}
