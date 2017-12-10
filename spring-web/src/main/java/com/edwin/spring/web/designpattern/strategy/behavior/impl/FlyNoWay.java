package com.edwin.spring.web.designpattern.strategy.behavior.impl;

import com.edwin.spring.web.designpattern.strategy.behavior.FlyBehavior;

/**
 * 不会飞
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can't fly...");
    }
}
