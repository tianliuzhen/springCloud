package com.aaa.security_oauth2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: wmlmicro-service-wechat
 * @description:
 * @author: weiwei.Xu
 * @create: 2019-04-13 15:08
 **/
public class TimeUtil {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String TIME_PATTERN = "HH:mm:ss";

    // 微信支付回调时间格式
    public static final String WXPAY_TIME_FORMAT = "yyyyMMddHHmmss";

    // 微信支付回调时间格式
    public static final String WXREFUND_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间unix时间戳
     * @return
     */
    public static long getTime() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前日期
     * @param format 格式 如 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getDate(String format) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(dateTime);
    }

    /**
     * 获取今日加/减后的日期
     * @param num 正负天数
     * @param format 格式 如 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getDayOffsetDate(int num, String format) {
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.plusDays(num);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(dateTime);
    }

    /**
     * 日期转unix时间戳
     * @param date
     * @param format 格式 如 "yyyy-MM-dd HH:mm:ss"
     * @return
     * @throws ParseException
     */
    public static long date2UnixTimestamp(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(8));
    }

    /**
     * unix时间戳转日期
     * @param unixTimestamp
     * @param format 格式 如 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String unixTimestamp2Date(long unixTimestamp, String format) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(unixTimestamp,0, ZoneOffset.ofHours(8));
        return DateTimeFormatter.ofPattern(format).format(dateTime);
    }

    /**
     * 获取今日剩余时间 s
     * @return
     */
    public static long getTodayCountDown() {
        long now = TimeUtil.getTime();
        long tomorrowTime = TimeUtil.date2UnixTimestamp(TimeUtil.getDayOffsetDate(1,"yyyyMMdd"),"yyyyMMdd");
        return tomorrowTime - now;
    }

    /**
     * 将时间戳转成LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static Date stringToDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }
    public static LocalDateTime getLocalDateTime(Date date) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime;
    }


    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将微信时间格式 转成  LocalDateTime
     * @param time 2009年12月25日9点10分10秒表示为20091225091010
     * @return
     */
    public static LocalDateTime getDateTimeByWXPayTime(String time) {
        Date date = stringToDate(time, WXPAY_TIME_FORMAT);
        return dateToLocalDateTime(date);
    }

    public static LocalDateTime getDateTimeByWXRefundTime(String time) {
        Date date = stringToDate(time, WXREFUND_TIME_FORMAT);
        return dateToLocalDateTime(date);
    }
}
