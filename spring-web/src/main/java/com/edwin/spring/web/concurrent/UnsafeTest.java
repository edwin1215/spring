package com.edwin.spring.web.concurrent;

/**
 * Unsafe类
 *
 * @author caojunming
 * @datetime 2020/9/6 3:10 PM
 */
public class UnsafeTest {

//    private volatile int a =10;
//    private volatile Node tail;
//    private static final long aOffset;
//    private static final long tailOffset;
//
//    private static final Unsafe UNSAFE;
//
//    static {
//        try {
//            UNSAFE = getUnsafe();
//            aOffset = UNSAFE.objectFieldOffset
//                    (UnsafeTest.class.getDeclaredField("a"));
//            tailOffset = UNSAFE.objectFieldOffset
//                    (UnsafeTest.class.getDeclaredField("tail"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//    public boolean changeA(int var1,int var2){
//        return UNSAFE.compareAndSwapObject(this,aOffset,var1,var2);
//    }
//
//    private final boolean compareAndSetTail(Node expect, Node update) {
//        return UNSAFE.compareAndSwapObject(this, tailOffset, expect, update);
//    }
//
//    private final String getAddr(Object o) {
//        long baseOffset = UNSAFE.arrayBaseOffset(Object[].class);
//        int addressSize = UNSAFE.addressSize();
//        long objectAddress;
//        switch (addressSize)
//        {
//            case 4:
//                objectAddress = UNSAFE.getInt(o, baseOffset);
//                break;
//            case 8:
//                objectAddress = UNSAFE.getLong(o, baseOffset);
//                break;
//            default:
//                throw new Error("unsupported address size: " + addressSize);
//        }
//
//        return(Long.toHexString(objectAddress));
//    }
//
//    /**
//     * 获取Unsafe对象的方法
//     * @return
//     */
//    public static Unsafe getUnsafe() {
//        try {
//            Field f = Unsafe.class.getDeclaredField("theUnsafe");
//            f.setAccessible(true);
//            return (Unsafe) f.get(null);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static void main(String[] args) {
//
//        UnsafeTest unsafeTest = new UnsafeTest();
//        boolean flag = unsafeTest.changeA(10, 20);//这里因为自动int属性的自动包装而导致了总是返回false
//        System.out.println(flag);
//
//        System.out.println(unsafeTest.a);
//
//        System.out.println("---------------");
//        Node before = new Node("before");
//        Node beforePrev = new Node("beforePrev");
//        Node beforeNext = new Node("beforeNext");
//        before.setPrev(beforePrev);
//        before.setNext(beforeNext);
//
//        Node after = new Node("after");
//        Node afterPrev = new Node("afterPrev");
//        Node afterNext = new Node("afterNext");
//        after.setPrev(afterPrev);
//        after.setNext(afterNext);
//
//        unsafeTest.setTail(before);
//
//        System.out.println(unsafeTest);
//        System.out.println(unsafeTest.getAddr(unsafeTest.getTail()));
//        unsafeTest.compareAndSetTail(before, after);
//        System.out.println(unsafeTest);
//        System.out.println(unsafeTest.getAddr(unsafeTest.getTail()));
//
//    }
//
//    static class Node {
//        String name;
//        volatile Node prev;
//        volatile Node next;
//
//        public Node(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Node getPrev() {
//            return prev;
//        }
//
//        public void setPrev(Node prev) {
//            this.prev = prev;
//        }
//
//        public Node getNext() {
//            return next;
//        }
//
//        public void setNext(Node next) {
//            this.next = next;
//        }
//
//        @Override
//        public String toString() {
//            return "{\"name\":\"" + name + "\"" +
//                    ", \"prev\":" + prev +
//                    ", \"next\":" + next +
//                    ", \"addr\":" + System.identityHashCode(this) +
//                    "}";
//        }
//    }
//
//    public Node getTail() {
//        return tail;
//    }
//
//    public void setTail(Node tail) {
//        this.tail = tail;
//    }
//
//    @Override
//    public String toString() {
//        return "UnsafeTest{" +
//                "tail=" + tail +
//                '}';
//    }
}
