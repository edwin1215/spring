package com.edwin.spring.web.designpattern.strategy.variety;

import com.edwin.spring.web.designpattern.strategy.Duck;
import com.edwin.spring.web.designpattern.strategy.behavior.FlyBehavior;

/**
 * 橡胶鸭
 */
public class RubberDuck extends Duck {

    public RubberDuck(FlyBehavior flyBehavior) {
        setFlyBehavior(flyBehavior);
    }

    @Override
    public void display() {
        System.out.println("I'm a rubber duck.");
    }
}
