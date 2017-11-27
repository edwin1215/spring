package com.edwin.spring.web.io.n;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ReadFile {

  public static void main(String[] args) {
    String path = "D:\\edwin\\document\\name\\nameb.txt";
    if (args != null && args.length > 0) {
      path = args[0];
    }
    readText(path);
  }

  public static void readText(String path) {
    FileInputStream fis = null;
    FileChannel channel = null;
    try {
      fis = new FileInputStream(new File(path));
      channel = fis.getChannel();
      ByteBuffer bBuffer = ByteBuffer.allocate(1024 * 100);
      // System.out.println("限制是：" + bBuffer.limit() + ", 容量是：" + bBuffer.capacity() + ", 位置是："
      // + bBuffer.position());
      int length = -1;
      while ((length = channel.read(bBuffer)) != -1) {
        System.out.println("限制是：" + bBuffer.limit() + ", 容量是：" + bBuffer.capacity() + ", 位置是："
            + bBuffer.position());
        /*
         * 读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
         */
        bBuffer.clear();
        byte[] bytes = bBuffer.array();
        String readStr = new String(bytes, 0, length);
        String[] split = readStr.split("\r\n");

        System.out.println(Arrays.toString(split));
        System.out.println(split.length);
        // System.out.write(bytes, 0, length);
        // System.out.println(new String(bytes));


      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (channel != null) {
        try {
          channel.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
