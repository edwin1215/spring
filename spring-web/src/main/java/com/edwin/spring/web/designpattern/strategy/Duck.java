package com.edwin.spring.web.designpattern.strategy;

import com.edwin.spring.web.designpattern.strategy.behavior.FlyBehavior;
import com.edwin.spring.web.designpattern.strategy.behavior.impl.FlyNoWay;
import com.edwin.spring.web.designpattern.strategy.behavior.impl.FlyWithWings;
import com.edwin.spring.web.designpattern.strategy.variety.RedheadDuck;
import com.edwin.spring.web.designpattern.strategy.variety.RubberDuck;

/**
 * 鸭子基类
 * (策略模式)
 */
public abstract class Duck {

    FlyBehavior flyBehavior;

    public abstract void display();

    void  swim() {
        System.out.println("I'm swimming very happy!");
    }

    void performFly() {
        flyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public static void main(String[] args) {
        Duck redheadDuck = new RedheadDuck(new FlyWithWings());
        redheadDuck.display();
        redheadDuck.swim();
        redheadDuck.performFly();

        Duck rubberDuck = new RubberDuck(new FlyNoWay());
        rubberDuck.display();
        rubberDuck.swim();
        rubberDuck.performFly();
    }
}
