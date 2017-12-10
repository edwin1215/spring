package com.edwin.spring.web.designpattern.strategy.variety;

import com.edwin.spring.web.designpattern.strategy.Duck;
import com.edwin.spring.web.designpattern.strategy.behavior.FlyBehavior;

/**
 * 红头鸭子
 */
public class RedheadDuck extends Duck {

    public RedheadDuck(FlyBehavior flyBehavior) {
        setFlyBehavior(flyBehavior);
    }

    @Override
    public void display() {
        System.out.println("I'm a redhead duck.");
    }
}
