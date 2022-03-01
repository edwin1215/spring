package com.edwin.spring.web.design.decorator;

import java.math.BigDecimal;

/**
 * 深度烘焙咖啡
 *
 * @author junming.cao
 * @date 2022/3/1 11:10 下午
 */
public class DarkRoast extends Beverage {

    @Override
    public String getDescription() {
        return "深度烘焙咖啡";
    }

    /**
     * 2.79 元 / 杯
     * @return 价格
     */
    @Override
    public double cost() {
        return 2.79D;
    }
}
