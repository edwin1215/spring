package com.edwin.spring.web.design.decorator;

/**
 * 黑咖啡（混合咖啡）
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class HouseBlend extends Beverage {

    @Override
    public String getDescription() {
        return "黑咖啡";
    }

    /**
     * 0.89 元 / 杯
     * @return 价格
     */
    @Override
    public double cost() {
        return 0.89D;
    }
}
