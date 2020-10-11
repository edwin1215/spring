package com.edwin.spring.web.io.n;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable{
        final Selector selector;
        final ServerSocketChannel serverSocket;
        Reactor(int port) throws IOException { //Reactor初始化
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false); //非阻塞
            SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT); //分步处理,第一步,接收accept事件
            sk.attach(new Acceptor()); //attach callback object, Acceptor
        }

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select();
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        dispatch(it.next()); //Reactor负责dispatch收到的事件
                        it.remove();
                    }
                }
            } catch (IOException ex) { /* ... */ }
        }

        private void dispatch(SelectionKey k) {
            Runnable r = (Runnable)(k.attachment()); //调用之前注册的callback对象
            if (r != null)
                r.run();
        }

        class Acceptor implements Runnable { // inner
            public void run() {
                try {
                    SocketChannel c = serverSocket.accept();
                    if (c != null)
                        new Handler(selector, c);
                }
                catch(IOException ex) { /* ... */ }
            }
        }

}

final class Handler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(10);
    ByteBuffer output = ByteBuffer.allocate(100);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c; c.configureBlocking(false);
        // Optionally try first read now
        sk = socket.register(sel, 0);
        sk.attach(this); //将Handler作为callback对象
        sk.interestOps(SelectionKey.OP_READ); //第二步,接收Read事件
        sel.wakeup();
    }
    boolean inputIsComplete() {
        return false;
    }
    boolean outputIsComplete() {
        return false;
    }
    void process() {

    }

    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE); //第三步,接收write事件
        }
    }
    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) sk.cancel(); //write完就结束了, 关闭select key
    }
}

