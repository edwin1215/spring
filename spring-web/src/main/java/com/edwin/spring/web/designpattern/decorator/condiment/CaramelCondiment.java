package com.edwin.spring.web.designpattern.decorator.condiment;

import com.edwin.spring.web.designpattern.decorator.Beverage;

/**
 * 焦糖
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class CaramelCondiment extends CondimentDecorator {

    public CaramelCondiment(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "(焦糖)";
    }

    /**
     * 1.2 元 / 份
     * @return 价格
     */
    @Override
    public double cost() {
        return beverage.cost() + 1.2D;
    }
}
