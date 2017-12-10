package com.edwin.spring.web.designpattern.observer.custom;

import com.edwin.spring.web.designpattern.observer.custom.obs.impl.CurrentCondition;
import com.edwin.spring.web.designpattern.observer.custom.obs.impl.StatisticsDisplay;
import com.edwin.spring.web.designpattern.observer.custom.sub.Subject;
import com.edwin.spring.web.designpattern.observer.custom.sub.impl.WeatherData;

import java.util.Scanner;

/**
 * 观察者模式
 */
public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentCondition currentCondition = new CurrentCondition(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        while (true) {
            System.out.println("-------->input three number split by ','");
            Scanner scanner = new Scanner(System.in);
            final String next = scanner.next();
            System.out.println("========> input string : " + next);
            try {
                String[] strings = next.split(",");
                if (strings != null && strings.length >= 3) {
                    double temperature = Double.parseDouble(strings[0]);
                    double humidity = Double.parseDouble(strings[1]);
                    double pressure = Double.parseDouble(strings[2]);
                    weatherData.measurementsChanged(temperature, humidity, pressure);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
