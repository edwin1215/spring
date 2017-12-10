package com.edwin.spring.web.designpattern.observer.java;

import java.util.Observable;
import java.util.Observer;

public class MainTest {

    Observer observer = new Observer() {
        @Override
        public void update(Observable o, Object arg) {

        }
    };

    Observable observable = new Observable();
}
