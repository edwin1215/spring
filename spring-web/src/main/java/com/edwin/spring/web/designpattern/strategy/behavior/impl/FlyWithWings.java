package com.edwin.spring.web.designpattern.strategy.behavior.impl;

import com.edwin.spring.web.designpattern.strategy.behavior.FlyBehavior;

/**
 * 翅膀飞行
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
