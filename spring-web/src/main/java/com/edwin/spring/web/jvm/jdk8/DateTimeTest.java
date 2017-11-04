package com.edwin.spring.web.jvm.jdk8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date/Time API
 * 
 * @author caojunming
 * @data 2017年11月4日 下午2:25:49
 */
public class DateTimeTest {

	public static final int NUM = 1000000;

	public static void main(String[] args) {
		testDateTimeAPI();
		testOldAPI();
	}

	public static void testDateTimeAPI() {
		long sTime = Clock.systemUTC().millis();
		try {
			for (int i = 0; i < NUM; i++) {
				LocalDateTime.now().format(
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long eTime = Clock.systemUTC().millis();
			System.out.println("=======DateTimeAPI excute time:"
					+ (eTime - sTime));
		}
	}

	public static void testOldAPI() {
		long sTime = Clock.systemUTC().millis();
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < NUM; i++) {
				df.format(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			long eTime = Clock.systemUTC().millis();
			System.out.println("=======testOldAPI excute time:"
					+ (eTime - sTime));
		}
	}
}
