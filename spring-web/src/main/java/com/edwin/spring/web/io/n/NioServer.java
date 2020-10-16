package com.edwin.spring.web.io.n;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NID server
 *
 * @author caojunming
 * @datetime 2020/9/27 12:04 AM
 */
public class NioServer {


    public static void main(String[] args) {
        try {
            // 建立一个serversocket套接字，socket
            ServerSocketChannel channel = ServerSocketChannel.open();
            // 将socket进行bind、listen操作
            channel.socket().bind(new InetSocketAddress(1988));
            // 设置socket为非阻塞
            channel.configureBlocking(false);
            // 建立selector对象
            Selector selector = Selector.open();
            // 注册select事件，事件类型accept，处理所有客户端建立连接的请求
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("accept监听注册完成");
            for (;;) {
                // 等待select事件触发
                int readyChannels = selector.select();
                System.out.println("收到" + readyChannels + "个事件通知");
                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = readyKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    it.remove();

                    if (next.isAcceptable()) {
                        SocketChannel sc = channel.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        InetSocketAddress remoteAddress = (InetSocketAddress) sc.getRemoteAddress();
                        System.out.println("收到来自port:" + remoteAddress.getPort() + "的accpet连接，建立read监听");

                    } else if (next.isReadable()) {
                        SocketChannel sc = (SocketChannel) next.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int read = sc.read(readBuffer);
                        if (read > 0) {
                            String msg = new String(readBuffer.array(), 0, read);
                            InetSocketAddress remoteAddress = (InetSocketAddress) sc.getRemoteAddress();

                            System.out.println("收到来自port:"+remoteAddress.getPort()+"的数据：" + msg);
                            ByteBuffer wrap = ByteBuffer.wrap(("您好，我已收到:" + msg).getBytes());
                            sc.write(wrap);
                        } else if (read < 0) {
                            sc.close();
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
