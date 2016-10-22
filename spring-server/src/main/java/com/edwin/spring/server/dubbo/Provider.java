package com.edwin.spring.server.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Edwin on 2016/10/20.
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring-provider.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}
