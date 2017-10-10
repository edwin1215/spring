package com.edwin.spring.web.hash;

/**
 * Created by Edwin on 2016/10/22.
 */
public class ServerNode {
    private String name;
    private String password;
    private String ip;
    private String port;

    public ServerNode(String name, String password, String ip, String port) {
        this.name = name;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServerNode{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
