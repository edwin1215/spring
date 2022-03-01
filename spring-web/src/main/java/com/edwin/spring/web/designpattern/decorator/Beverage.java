package com.edwin.spring.web.designpattern.decorator;


/**
 * 饮料基类
 *
 * @author junming.cao
 * @date 2022/3/1 11:05 下午
 */
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    /**
     * 计算费用
     *
     * @return 价格，单位（元）
     */
    public abstract double cost();
}
