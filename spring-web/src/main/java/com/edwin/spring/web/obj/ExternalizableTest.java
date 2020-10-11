package com.edwin.spring.web.obj;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * @author caojunming
 * @datetime 2020/10/11 11:13 AM
 */
public class ExternalizableTest implements Externalizable {
    private static final long serialVersionUID = 4443146687125421268L;

    private String username;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readObject();
    }

    public static void main(String[] args) {
        ExternalizableTest t = new ExternalizableTest();
        t.setName("balabala");
        t.setAge(12);
        t.setUsername("bbbbb");
        t.setPwd("aaaaa");
    }
}
