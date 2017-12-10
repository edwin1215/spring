package com.edwin.spring.web.designpattern.observer.custom.obs.impl;

import com.edwin.spring.web.designpattern.observer.custom.obs.DisplayElement;
import com.edwin.spring.web.designpattern.observer.custom.obs.Observer;
import com.edwin.spring.web.designpattern.observer.custom.sub.Subject;

public class StatisticsDisplay implements Observer, DisplayElement {

    private double temperature;
    private double humidity;
    private double pressure;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        register(this.weatherData);
    }

    @Override
    public void display() {
        System.out.println("StatisticsDisplay min : " + (temperature > humidity ? (humidity > pressure ? pressure : humidity) : (temperature > pressure ? pressure : temperature)));
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void register(Subject subject) {
        subject.register(this);
    }

    @Override
    public void remove(Subject subject) {
        subject.remove(this);
    }
}
