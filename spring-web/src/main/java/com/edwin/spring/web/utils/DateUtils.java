package com.edwin.spring.web.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author caojunming
 * @date 2021/1/22
 */
@Slf4j
public class DateUtils {
    public static final String DB_NULL_DATETIME = "2000-01-01 00:00:00";

    private DateUtils() {
    }

    private static SimpleDateFormat getDateFormat(String parttern) {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 返回当前时间字符串，yyyyMMdd
     * @return
     */
    public static String currentDate(){
        return DateUtils.date2String(new Date(),DateStyleEnum.YYYYMMDD);
    }

    /**
     * 返回当前时间字符串，yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String currentDateStr(){
        return DateUtils.date2String(new Date(),DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date string2Date(String date) {
        return string2Date(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date string2Date(String date, DateStyleEnum dateStyleEnum) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(dateStyleEnum.getValue()).parse(date);
            } catch (Exception e) {
                log.warn("string2Date occurs some error", e);
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String date2String(Date date, DateStyleEnum dateStyleEnum) {
        String myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(dateStyleEnum.getValue()).format(date);
            } catch (Exception e) {
                log.warn("date2String occurs some error", e);
            }
        }
        return myDate;
    }

    /* ******************** java.time.* ******************** */

    /**
     * LocalTime -> String
     *
     * @param localTime
     * @param dateStyleEnum
     * @return
     */
    public static String localTime2String(LocalTime localTime, DateStyleEnum dateStyleEnum) {
        String myDate = null;
        if (localTime != null) {
            try {
                myDate = localTime.format(DateTimeFormatter.ofPattern(dateStyleEnum.getValue()));
            } catch (Exception e) {
                log.warn("localTime2String error", e);
            }
        }
        return myDate;
    }

    /**
     * LocalTime -> String，默认格式
     *
     * @param localTime
     * @return
     */
    public static String localTime2String(LocalTime localTime) {
        return localTime2String(localTime, DateStyleEnum.HH_MM_SS);
    }

    /**
     * String -> LocalTime
     *
     * @param strDate
     * @param dateStyleEnum
     * @return
     */
    public static LocalTime string2LocalTime(String strDate, DateStyleEnum dateStyleEnum) {
        try {
            return LocalTime.parse(strDate, DateTimeFormatter.ofPattern(dateStyleEnum.getValue()));
        } catch (Exception e) {
            log.error("string2LocalTime error", e);
            return null;
        }
    }

    /**
     * String -> LocalTime，默认格式
     *
     * @param strDate
     * @return
     */
    public static LocalTime string2LocalTime(String strDate) {
        return string2LocalTime(strDate, DateStyleEnum.HH_MM_SS);
    }

    /**
     * String -> LocalDateTime
     *
     * @param strDate
     * @param dateStyleEnum
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String strDate, DateStyleEnum dateStyleEnum) {
        try {
            return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(dateStyleEnum.getValue()));
        } catch (Exception e) {
            log.error("string2LocalDateTime error", e);
            return null;
        }
    }

    /**
     * String -> LocalDateTime，默认格式
     *
     * @param strDate
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String strDate) {
        return string2LocalDateTime(strDate, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * LocalDateTime -> String
     *
     * @param localDateTime
     * @param styleEnum
     * @return
     */
    public static String localDateTime2String(LocalDateTime localDateTime, DateStyleEnum styleEnum) {
        try {
            return localDateTime.format(DateTimeFormatter.ofPattern(styleEnum.getValue()));
        } catch (Exception e) {
            log.error("localDateTime2String error", e);
            return null;
        }
    }

    /**
     * LocalDateTime -> String，默认格式
     *
     * @param localDateTime
     * @return
     */
    public static String localDateTime2String(LocalDateTime localDateTime) {
        return localDateTime2String(localDateTime, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * String -> LocalDate
     *
     * @param strDate
     * @param dateStyleEnum
     * @return
     */
    public static LocalDate string2LocalDate(String strDate, DateStyleEnum dateStyleEnum) {
        try {
            return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(dateStyleEnum.getValue()));
        } catch (Exception e) {
            log.error("string2LocalDate error", e);
            return null;
        }
    }

    /**
     * String -> LocalDate，默认格式
     *
     * @param strDate
     * @return
     */
    public static LocalDate string2LocalDate(String strDate) {
        return string2LocalDate(strDate, DateStyleEnum.YYYY_MM_DD);
    }

    /**
     * LocalDate -> String
     *
     * @param localDate
     * @param styleEnum
     * @return
     */
    public static String localDate2String(LocalDate localDate, DateStyleEnum styleEnum) {
        try {
            return localDate.format(DateTimeFormatter.ofPattern(styleEnum.getValue()));
        } catch (Exception e) {
            log.error("localDate2String error", e);
            return null;
        }
    }

    /**
     * LocalDate -> String，默认格式
     *
     * @param localDate
     * @return
     */
    public static String localDate2String(LocalDate localDate) {
        return localDate2String(localDate, DateStyleEnum.YYYY_MM_DD);
    }

    /**
     * 是否在时间段范围内
     *
     * @param current
     * @param from
     * @param to
     * @return
     */
    public static boolean withinLocalTime(LocalTime current, LocalTime from, LocalTime to) {
        return current.isAfter(from) && current.isBefore(to);
    }

    /**
     * 当前时间是否在时间段范围内
     *
     * @param from
     * @param to
     * @return
     */
    public static boolean withinLocalTimeNow(LocalTime from, LocalTime to) {
        return withinLocalTime(LocalTime.now(), from, to);
    }


    /**
     * 获取两个时间相差分钟数
     *
     * @param before
     * @param after
     * @return
     */
    public static int durationMinute(LocalTime before, LocalTime after) {
        return (int) Duration.between(before, after).toMinutes();
    }


    public static int durationDayNow(String localDateStr) {
        return durationDayNow(string2LocalDate(localDateStr));
    }

    public static int durationDayNow(LocalDate after) {
        return durationDay(LocalDate.now().atStartOfDay(), after.atStartOfDay());
    }

    /**
     * 获取两个LocalDate相差天数
     *
     * @param before
     * @param after
     * @return
     */
    public static int durationDay(LocalDate before, LocalDate after) {
        return durationDay(before.atStartOfDay(), after.atStartOfDay());
    }

    /**
     * 获取两个LocalDateTime相差天数
     *
     * @param before
     * @param after
     * @return
     */
    public static int durationDay(LocalDateTime before, LocalDateTime after) {
        return (int) Duration.between(before, after).toDays();
    }

    /**
     * 获取指定日期的最小时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime dayEarliest(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * 获取指定日期的最大时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime dayLatest(LocalDate localDate) {
        return localDate.atTime(23, 59, 59);
    }

    /**
     * 获取当天最小时间
     *
     * @return
     */
    public static LocalDateTime nowDayEarliest() {
        return LocalDate.now().atStartOfDay();
    }

    /**
     * 获取当天最大时间
     *
     * @return
     */
    public static LocalDateTime nowDayLatest() {
        return LocalDate.now().atTime(LocalTime.MAX);
    }

    /**
     * 获取最小时间
     *
     * @return
     */
    public static LocalTime nowTimeEarliest() {
        return LocalTime.of(0, 0, 1);
    }

    /**
     * 获取当前最大时间
     *
     * @return
     */
    public static LocalTime nowTimeLatest() {
        return LocalTime.MAX;
    }

    /**
     * 获取当前日期时间的格式化字符串
     *
     * @param dateStyleEnum
     * @return
     */
    public static String nowLocalDateTime(DateStyleEnum dateStyleEnum) {
        return localDateTime2String(LocalDateTime.now(), dateStyleEnum);
    }

    /**
     * 获取当前日期时间的格式化字符串，默认格式
     *
     * @return
     */
    public static String nowLocalDateTime() {
        return localDateTime2String(LocalDateTime.now());
    }

    /**
     * 获取当前日期的格式化字符串，默认格式
     *
     * @return
     */
    public static String nowLocalDate() {
        return localDate2String(LocalDate.now());
    }

    /**
     * 获取当前时间的格式化字符串，默认格式
     *
     * @return
     */
    public static String nowLocalTime() {
        return localTime2String(LocalTime.now());
    }

    /**
     * 数据库默认日期
     *
     * @return
     */
    public static LocalDateTime defaultDBLocalDateTime() {
        return string2LocalDateTime(DB_NULL_DATETIME);
    }

}
