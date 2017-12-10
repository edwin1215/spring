package com.edwin.spring.web.designpattern.observer.custom.obs.impl;

import com.edwin.spring.web.designpattern.observer.custom.obs.DisplayElement;
import com.edwin.spring.web.designpattern.observer.custom.obs.Observer;
import com.edwin.spring.web.designpattern.observer.custom.sub.Subject;

/**
 * 公告板1，当前状态
 */
public class CurrentCondition implements Observer, DisplayElement {
    private double temperature;
    private double humidity;
    private double pressure;
    private Subject weatherData;

    public CurrentCondition(Subject weatherData) {
        this.weatherData = weatherData;
        register(this.weatherData);
    }
    @Override
    public void register(Subject subject) {
        subject.register(this);
    }

    @Override
    public void remove(Subject subject) {
        subject.remove(this);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions : " + temperature + "F degrees and " + humidity + "%humidity and pressure is " + pressure);
    }
}
