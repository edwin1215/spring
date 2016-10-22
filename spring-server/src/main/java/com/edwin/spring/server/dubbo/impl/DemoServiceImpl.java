package com.edwin.spring.server.dubbo.impl;

import com.edwin.spring.server.dubbo.DemoService;

/**
 * Created by Edwin on 2016/10/20.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
