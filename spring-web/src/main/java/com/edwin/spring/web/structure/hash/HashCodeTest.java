package com.edwin.spring.web.structure.hash;

public class HashCodeTest {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashCodeTest(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) {
        HashCodeTest h1 = new HashCodeTest(1);
        HashCodeTest h2 = new HashCodeTest(2);
        System.out.println(h1.hashCode());
        System.out.println(h2.hashCode());
    }
}
