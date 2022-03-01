package com.edwin.spring.web.designpattern.decorator.condiment;

import com.edwin.spring.web.designpattern.decorator.Beverage;

/**
 * 牛奶
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class MilkCondiment extends CondimentDecorator {

    public MilkCondiment(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "(牛奶)";
    }

    /**
     * 1.5 元 / 份
     * @return 价格
     */
    @Override
    public double cost() {
        return beverage.cost() + 1.5D;
    }
}
