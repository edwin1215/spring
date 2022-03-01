package com.edwin.spring.web.designpattern.decorator.condiment;

import com.edwin.spring.web.designpattern.decorator.Beverage;

/**
 * 摩卡
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class MochaCondiment extends CondimentDecorator {

    public MochaCondiment(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "(摩卡)";
    }

    /**
     * 0.2 元 / 份
     * @return 价格
     */
    @Override
    public double cost() {
        return beverage.cost() + 0.2D;
    }
}
