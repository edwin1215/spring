package com.edwin.spring.web.io.n;

import com.edwin.spring.web.tools.PrintUtils;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO client
 *
 * @author caojunming
 * @datetime 2020/9/27 1:23 AM
 */
public class NioClient {

    private final static int THREADS = 2;
    private static List<SocketChannel> scList = new ArrayList<>(THREADS);
    private static ExecutorService es = Executors.newFixedThreadPool(THREADS);

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < THREADS; i++) {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 1988));
            scList.add(sc);
        }

        System.out.println("创建客户端");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next == null || next.contains("end")) {
                break;
            }

            for (SocketChannel sc : scList) {
                es.execute(() -> {
                    try {
                        // 发送请求
                        ByteBuffer buffer = ByteBuffer.wrap(next.getBytes());
                        sc.write(buffer);
                        // sendfile
//                        FileChannel fileChannel = new FileInputStream("").getChannel();
//                        fileChannel.transferTo(0, fileChannel.size(), socketChannel);
                        // mmap
//                        IntBuffer intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size()).asIntBuffer();
//                        for (int i = 0; i < fileChannel.size(); i++) {
//                            intBuffer.put(i);
//                        }
                        // 读取响应
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int num;
                        if ((num = sc.read(readBuffer)) > 0) {
                            readBuffer.flip();

                            byte[] re = new byte[num];
                            readBuffer.get(re);

                            String result = new String(re, "UTF-8");
                            PrintUtils.sys("server响应: " + result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
