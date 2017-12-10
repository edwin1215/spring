package com.edwin.spring.web.designpattern.observer.custom.sub.impl;

import com.edwin.spring.web.designpattern.observer.custom.obs.Observer;
import com.edwin.spring.web.designpattern.observer.custom.sub.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气预报
 */
public class WeatherData implements Subject {

    private List<Observer> observers;
    private double temperature;
    private double humidity;
    private double pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notice() {
        observers.stream().forEach(o -> o.update(temperature, humidity, pressure));
    }

    /**
     * 测试变化
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void measurementsChanged(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notice();
    }
}
