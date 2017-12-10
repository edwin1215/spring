package com.edwin.spring.web.designpattern.observer.custom.obs;

import com.edwin.spring.web.designpattern.observer.custom.sub.Subject;

/**
 * 观察者
 */
public interface Observer {

    /**
     * 更新数据
     *
     * @param temperature 温度
     * @param humidity    湿度
     * @param pressure    气压
     */
    void update(double temperature, double humidity, double pressure);

    /**
     * 订阅
     *
     * @param subject
     */
    void register(Subject subject);

    /**
     * 取消订阅
     *
     * @param subject
     */
    void remove(Subject subject);
}
