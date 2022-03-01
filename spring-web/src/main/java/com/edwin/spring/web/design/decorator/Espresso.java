package com.edwin.spring.web.design.decorator;

/**
 * 浓缩咖啡
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class Espresso extends Beverage {

    @Override
    public String getDescription() {
        return "浓缩咖啡";
    }

    /**
     * 1.99 元 / 杯
     * @return 价格
     */
    @Override
    public double cost() {
        return 1.99D;
    }
}
