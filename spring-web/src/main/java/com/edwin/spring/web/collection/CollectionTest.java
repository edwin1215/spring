package com.edwin.spring.web.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class CollectionTest {

    public static void main(String[] args) {
        testClone();

        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(6 >> 1);
    }

    public static void testCollection() {
        Collection<String> col = new ArrayList<>();
        Collections.sort(new ArrayList<String>());

        Map<String, String> stringStringMap = Collections.synchronizedMap(new HashMap<>());

        stringStringMap.put("test", "1");
    }

    public void testHashSet() {
        HashSet<String> set = new HashSet<>();
        set.add("123");
        set.add("456");
        set.remove("123");
    }

    public static void testTreeSet() {
        TreeSet<String> set = new TreeSet<>();
        set.add("test");
        set.remove("test");
    }

    public static void testHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "1");
        map.get("test");
        map.putAll(new HashMap<>());
        map.remove("test");
        map.containsValue("test");
    }

    public static void testHashtable() {
        Hashtable<String, String> table = new Hashtable<>();
        table.put("test1", "1");
        table.put("test2", "2");
        table.get("test1");
        table.remove("test2");
        table.contains("test");
        table.containsValue("test");
    }

    public static void testLinkedHashMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("test", "1");
    }

    public static void testConcurrentHashMap() {
        ConcurrentHashMap<String, String> table = new ConcurrentHashMap<>();
        table.put("test1", "1");
        table.put("test2", "2");
        table.get("test1");
        table.remove("test2");
        table.contains("test");
        table.containsValue("test");
    }

    public static void testArrayList() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("test");
        arr.addAll(new ArrayList<String>());
        arr.remove("test");
    }

    public static void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        list.addAll(new LinkedList<>());
        list.remove("test");
    }

    public static void testClone() {
        HashMap<String, String> map = new HashMap<>();
        map.put("test", "1");

        HashMap<String, String> clone = (HashMap<String, String>) map.clone();
        System.out.println(clone);
        System.out.println("----------testClone----------");
    }
}
