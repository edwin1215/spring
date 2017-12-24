package com.edwin.spring.web.jvm.memoryleakage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 2、当集合里面的对象属性被修改后，再调用remove（）方法时不起作用。
 * (无效)
 */
@Deprecated
public class CollectionUpd {

    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("aaa", 1, 23);
        Person p2 = new Person("bbb", 0, 24);
        Person p3 = new Person("ccc", 1, 27);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("set size:" + set.size());
        p3.setAge(2);
        set.stream().forEach(person -> System.out.println(person.hashCode()));
        System.out.println("----------");
        set.remove(p3);
        set.stream().forEach(person -> System.out.println(person.hashCode()));
        System.out.println("----------");
        set.add(p3);
        System.out.println("set size:" + set.size());
        set.stream().forEach(person -> System.out.println(person.hashCode()));
    }
}

class Person {
    private String name;
    private int sex;
    private int age;

    public Person(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}