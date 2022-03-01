package com.edwin.spring.web.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author caojunming
 * @datetime 2020/9/6 1:51 PM
 */
public class AtomicTest {

    private transient volatile Node head;
    private transient volatile Node tail;

    AtomicReference<Node> reference = new AtomicReference();

    public static void main(String[] args) {
        AtomicTest test = new AtomicTest();
        Node node = new Node("node");
        test.head = new Node("shao");
        test.tail = test.head;
//        Node t = test.tail;

        System.out.println("node:" + node);
        System.out.println("main:" + test);
    }

    static class Node {
        String name;
        volatile Node prev;
        volatile Node next;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AtomicTest{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }
}
