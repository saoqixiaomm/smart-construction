/*
 * Miya.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.xd.smartconstruction.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author ${jiangjiangbo}
 * @version $Id: DateUtil.java, v 0.1 2019年06月04日 18:59 ${jiangjiangbo} Exp $
 */
@Slf4j
public class DateUtil {

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYY_MM_DD_24HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYY_MM_DD_START = "yyyy-MM-dd 00:00:00";

    public final static String YYYY_MM_DD_END = "yyyy-MM-dd 23:59:59";

    public final static String YYYY_MM_DD_HH_SS = "yyyy-MM-dd hh:mm:ss";

    /**
     * yyyyMMdd
     */
    public static final String YYYYMMDD = "yyyyMMdd";


    public final static String HH_MM = "hh:mm";

    public final static String HH24 = "HH:mm";

    public final static String MM_DD = "MM_DD";

    /**
     * 时间格式
     **/
    public static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 00时00分
     **/
    public static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH时mm分");

    public static List<Date> getFirstAndLastDay() throws Exception {
        List<Date> list = Lists.newArrayListWithExpectedSize(2);
        // 获取当月第一天和最后一天
        SimpleDateFormat firstDayformat = new SimpleDateFormat(YYYY_MM_DD_START);
        SimpleDateFormat lastDayformat = new SimpleDateFormat(YYYY_MM_DD_END);

        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_24HH_MM_SS);

        String firstday, lastday;
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = firstDayformat.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = lastDayformat.format(cale.getTime());

        list.add(sdf.parse(firstday));
        list.add(sdf.parse(lastday));


        return list;
    }


    /**
     * date转换为LocalDateTime
     *
     * @param date Date类型的参数
     * @return LocalDateTime
     **/
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 求两天相差的天数
     *
     * @param fDate 起始时间
     * @param oDate 结束时间
     * @return 天数
     */
    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(oDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return Math.abs(day2 - day1);
    }


    /**
     * 增加指定的天数
     *
     * @param days 指定的天数
     * @return 时间
     */
    public static Date plusDays(int days) {
        LocalDate now = LocalDate.now().plusDays(days);
        LocalTime time = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(now, time);
        return convertLocalDateTime(localDateTime);
    }

    public static Date convertLocalDateTime(LocalDateTime localDateTime) {
        return convertLocalDateTime(localDateTime, YYYY_MM_DD_24HH_MM_SS);
    }

    /**
     * 增加指定的天数
     *
     * @param days 指定的天数
     * @return 时间
     */
    public static Date plusDays(int days, String format) {
        LocalDate now = LocalDate.now().plusDays(days);
        LocalTime time = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(now, time);
        return convertLocalDateTime(localDateTime, format);
    }


    public static Date convertLocalDateTime(LocalDateTime localDateTime, String pattern) {
        String formatTime = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        Date parseDate = parseDate(formatTime, pattern);
        return parseDate;
    }

    public static String getEndOfDay(String dateStr) {
        Date date = parseDate(dateStr, DateUtil.YYYY_MM_DD);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return parseDate(Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()), DateUtil.YYYY_MM_DD_24HH_MM_SS);
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getStartOfDay(String dateStr) {
        Date date = parseDate(dateStr, DateUtil.YYYY_MM_DD);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return parseDate(Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()), DateUtil.YYYY_MM_DD_24HH_MM_SS);
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String format(Date date) {
        return new SimpleDateFormat(YYYY_MM_DD_24HH_MM_SS).format(date);
    }


    public static String parseDate(Date d, String formatStr) {
        try {
            return new SimpleDateFormat(formatStr).format(d);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDate(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 格式化时间
     *
     * @param date    date时间对象
     * @param pattern 格式
     * @return 格式化时间
     */
    public static String format(Date date, String pattern) {
        if (Objects.isNull(date) || StringUtils.isBlank(pattern)) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime LocalDateTime类型的参数
     * @return Date
     **/
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date转换为localDate
     *
     * @param date Date
     * @return LocalDate
     **/
    public static LocalDate dateToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 获得一天最开始的时间
     *
     * @param date
     * @return 00:00:00
     */
    public static Date gainTheDayStart(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime dateTime = dateToLocalDateTime(date);
        dateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN);
        return localDateTimeToDate(dateTime);
    }

    /**
     * 获得一天最后的时间
     *
     * @param date 日期
     * @return Date 23:59:59
     */
    public static Date gainTheDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.of(23, 59, 59, 0));
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据传入的年-月获取当月第一天
     *
     * @param month 2019-01
     * @return 00:00:00
     */
    public static Date gainTheMonthStart(String month) {
        if (StringUtils.isBlank(month)) {
            return null;
        }

        return localDateTimeToDate(LocalDateTime.parse(month + "-01 00:00:00",
                DATETIME_FORMATTER).with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 根据传入的年-月获取当月最后一天
     *
     * @param month 2019-07
     * @return Date
     */
    public static Date gainTheMonthEnd(String month) {
        if (StringUtils.isBlank(month)) {
            return null;
        }

        return localDateTimeToDate(LocalDateTime.parse(month + "-01 23:59:59",
                DATETIME_FORMATTER).with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * 根据传入的年-月获取当月第一天
     *
     * @param date 2019-11-11 01:01:01
     * @return 2019-11-01 00:00:00
     */
    public static Date gainTheMonthStart(Date date) {
        if (null == date) {
            return null;
        }

        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTimeToDate(LocalDateTime.of(localDateTime.toLocalDate().with(TemporalAdjusters.firstDayOfMonth()),
                LocalTime.MIN));
    }


    /**
     * 根据传入的年-月获取当月第一天
     *
     * @param date 2019-11-11 01:01:01
     * @return 2019-11-30 23:59:59
     */
    public static Date gainTheMonthEnd(Date date) {
        if (null == date) {
            return null;
        }

        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTimeToDate(LocalDateTime.of(localDateTime.toLocalDate().with(TemporalAdjusters.lastDayOfMonth()),
                LocalTime.MAX));
    }

}