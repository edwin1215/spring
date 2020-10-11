package com.edwin.spring.web.obj;

import java.util.Arrays;

/**
 * @author caojunming
 * @datetime 2020/9/12 1:39 PM
 */
public class CloneTest {


    public static void main(String[] args) throws CloneNotSupportedException {
        SubCloneClass sc = new SubCloneClass();
        sc.setSubA(66);
        sc.setSubAs(new int[]{77, 88, 99});
        CloneClass c = new CloneClass();
        c.setA(11);
        c.setAs(new int[]{22, 33, 44});
        c.setCloneClass(sc);

        System.out.println(c);
        CloneClass clone = (CloneClass)c.clone();
        System.out.println(clone);
    }

    static class CloneClass implements Cloneable {
        private int a;
        private int[] as;
        private SubCloneClass cloneClass;
        private SubCloneClass[] cloneClasss;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            CloneClass clone = (CloneClass) super.clone();
            clone.setCloneClass((SubCloneClass) clone.getCloneClass().clone());
            return clone;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int[] getAs() {
            return as;
        }

        public void setAs(int[] as) {
            this.as = as;
        }

        public SubCloneClass getCloneClass() {
            return cloneClass;
        }

        public void setCloneClass(SubCloneClass cloneClass) {
            this.cloneClass = cloneClass;
        }

        public SubCloneClass[] getCloneClasss() {
            return cloneClasss;
        }

        public void setCloneClasss(SubCloneClass[] cloneClasss) {
            this.cloneClasss = cloneClasss;
        }

        @Override
        public String toString() {
            return "{\"a\":" + a +
                    ", \"as\":\"" + as +
                    "\", \"cloneClass\":" + cloneClass +
                    ", \"cloneClasss\":\"" + cloneClasss +
                    "\", \"addr\":\"" + System.identityHashCode(this) +
                    "\"}";
        }
    }

    static class SubCloneClass implements Cloneable {
        private int subA;
        private int[] subAs;

        public int getSubA() {
            return subA;
        }

        public void setSubA(int subA) {
            this.subA = subA;
        }

        public int[] getSubAs() {
            return subAs;
        }

        public void setSubAs(int[] subAs) {
            this.subAs = subAs;
        }

        @Override
        public String toString() {
            return "{\"subA\":" + subA +
                    ", \"subAs\":\"" + subAs +
                    "\", \"addr\":\"" + System.identityHashCode(this) +
                    "\"}";
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
