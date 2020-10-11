package com.edwin.spring.web.io.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO server
 *
 * @author caojunming
 * @datetime 2020/9/27 1:05 AM
 */
public class BioServerTest {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(1988);

        System.out.println("开始监听。");
        while (true) {
            Socket s = ss.accept();
            System.out.println("有新连接------------port：" + s.getPort());
            InputStream in = s.getInputStream();
            byte[] b = new byte[1024];
            int read = in.read(b);
            String msg = null;
            if (read > 0) {
                msg = new String(b, 0, read);
                System.out.println("server 收到：" + msg);
            }
            OutputStream out = s.getOutputStream();
            out.write(msg == null ? "啥玩意不传啊?".getBytes() : msg.getBytes());
            out.flush();

            in.close();
            out.flush();
        }
    }
}
