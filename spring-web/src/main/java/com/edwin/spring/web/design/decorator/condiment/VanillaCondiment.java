package com.edwin.spring.web.design.decorator.condiment;

import com.edwin.spring.web.design.decorator.Beverage;

/**
 * 香草
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class VanillaCondiment extends CondimentDecorator {

    public VanillaCondiment(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "(香草)";
    }

    /**
     * 0.89 元 / 份
     * @return 价格
     */
    @Override
    public double cost() {
        return beverage.cost() + 0.89D;
    }
}
