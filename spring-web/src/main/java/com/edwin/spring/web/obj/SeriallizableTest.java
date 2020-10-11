package com.edwin.spring.web.obj;

import java.io.*;

/**
 * @author caojunming
 * @datetime 2020/10/11 10:56 AM
 */
public class SeriallizableTest implements Serializable {

    private static final long serialVersionUID = -8715187638557098445L;
//    private static final long serialVersionUID = -8715187638557098435L;

    private static String username = "666";
    private transient String pwd;
    private String name;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SeriallizableTest o = new SeriallizableTest();
        o.setName("xixi");
        o.setAge(1);
        o.setUsername("cxw");
        o.setPwd("xxxx");

        String path = "/Users/caojunming/Documents/text.txt";
        write(o, path);

        read(path);
    }

    private static void write(SeriallizableTest o, String path) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(path)));
        out.writeObject(o);
        out.flush();
        out.close();
        System.out.println("序列化完成");
    }

    private static void read(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(path)));
        SeriallizableTest readObject = (SeriallizableTest) in.readObject();
        in.close();
        System.out.println(readObject);
    }

    @Override
    public String toString() {
        return "SeriallizableTest{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
