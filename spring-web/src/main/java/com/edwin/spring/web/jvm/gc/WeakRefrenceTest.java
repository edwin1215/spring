package com.edwin.spring.web.jvm.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 * <p>
 * <-verbose:gc>
 * <-XX:+PrintGCDetails>
 */
public class WeakRefrenceTest {
    public static void main(String[] args) {
        NodeA a = new NodeA();
        a.name = "hello";
        WeakReference<NodeA> wr = new WeakReference<NodeA>(a);
        NodeB b = new NodeB(a);
//        a = null;
        int i = 0;
        while (true) {

            if (wr.get() != null) {
                i++;
                System.out.println("weak node : " + i);
            } else {
                System.out.println("already has been collated");
                break;
            }
        }
//        SoftReference
    }
}

class NodeA {
    public String name;
}

class NodeB {
    NodeA a;

    public NodeB(NodeA a) {
        this.a = a;
    }
}