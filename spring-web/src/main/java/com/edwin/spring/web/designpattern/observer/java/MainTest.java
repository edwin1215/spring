package com.edwin.spring.web.designpattern.observer.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class MainTest {

    Observer observer = new Observer() {
        @Override
        public void update(Observable o, Object arg) {

        }
    };

    Observable observable = new Observable();

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        long startTime = 1420041600000L;
        c.setTimeInMillis(2012958020734L + startTime);
        System.out.println(df.format(c.getTime()));
    }
}
