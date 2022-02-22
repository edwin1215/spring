package com.edwin.spring.web.utils;

/**
 * @author caojunming
 * @date 2021/1/22
 */
public enum DateStyleEnum {

    MM_DD("MM-dd"),
    YYYY_MM("yyyy-MM"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYYMMDD("yyyyMMdd"),
    YYYYMMDDHH("yyyyMMddHH"),
    YYYYMMDDHHMM("yyyyMMddHHmm"),
    YYYYMMDD_HHMM("yyyyMMdd_HHmm"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    YYYY_MM_DD_HH("yyyy-MM-dd-HH"),
    MM_DD_HH_MM("MM-dd HH:mm"),
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
    YYYY_MM_DDHH_MM_SS("yyyy-MM-ddHH:mm:ss"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss SSS"),

    MM_DD_EN("MM/dd"),
    YYYY_MM_EN("yyyy/MM"),
    YYYY_MM_DD_EN("yyyy/MM/dd"),
    MM_DD_HH_MM_EN("MM/dd HH:mm"),
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),

    MM_DD_CN("MM月dd日"),
    M_D_CN("M月d日"),
    YYYY_MM_CN("yyyy年MM月"),
    YYYY_MM_DD_CN("yyyy年MM月dd日"),
    MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
    M_D_HH_MM_CN("M月d日HH:mm"),
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),

    HH_MM("HH:mm"),
    HH_MM_SS("HH:mm:ss");


    private final String value;

    DateStyleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
