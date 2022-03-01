package com.edwin.spring.web.designpattern.decorator.condiment;

import com.edwin.spring.web.designpattern.decorator.Beverage;

/**
 * 调料装饰者基类
 *
 * @author junming.cao
 * @date 2022/3/1 11:09 下午
 */
public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;
    public abstract String getDescription();
}
