package com.edwin.spring.web.io.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * BIO client
 *
 * @author caojunming
 * @datetime 2020/9/27 1:05 AM
 */
public class BioClientTest {

    public static void main(String[] args) throws IOException {

        System.out.println("启动客户端");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.next();
            if (next == null || next.trim().equals("")) {
                continue;
            }
            Socket s = null;
            OutputStream out = null;
            InputStream in = null;

            try {
                s = new Socket("localhost", 1988);
                out = s.getOutputStream();
                out.write(next.getBytes());
                out.flush();
                // 接收响应
                in = s.getInputStream();
                int receiveBytes = 0;
                byte[] receiveBuffer = new byte[1024];

                if ((receiveBytes = in.read(receiveBuffer)) != -1) {
                    System.out.println("服务ack消息->" + new String(receiveBuffer, 0, receiveBytes));
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (s != null) {
                        s.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
