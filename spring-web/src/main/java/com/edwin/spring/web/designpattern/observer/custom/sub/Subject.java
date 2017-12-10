package com.edwin.spring.web.designpattern.observer.custom.sub;

import com.edwin.spring.web.designpattern.observer.custom.obs.Observer;

/**
 * 主题
 */
public interface Subject {
    /**
     * 订阅
     *
     * @param observer
     */
    void register(Observer observer);

    /**
     * 取消订阅
     *
     * @param observer
     */
    void remove(Observer observer);

    /**
     * 发布
     */
    void notice();
}
