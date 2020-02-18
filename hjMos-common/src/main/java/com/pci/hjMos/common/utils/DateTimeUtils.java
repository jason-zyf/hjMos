package com.pci.hjMos.common.utils;

import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class DateTimeUtils {


    public static String format(LocalDateTime dateTime, DateTimeFormatter formatter) {
        Assert.notNull(dateTime, "dateTime must not be null");
        if (formatter == null) {
            formatter = DateTimeFormatter.ISO_DATE_TIME;
        }
        return dateTime.format(formatter);
    }

    public static String format(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(localDateTime);
    }

    public static String format(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        return df.format(localDate);
    }

    public static String format1(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMM");
        return df.format(localDate);
    }

    public static String format2(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy");
        return df.format(localDate);
    }

    public static LocalDateTime parse(String dateStr) {
        DateTimeFormatter formatter;

        // 解析 DATETIME_FORMAT0 时间格式
        try {
            formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd HH:mm:ss")
                    .appendValue(ChronoField.MILLI_OF_SECOND, 3)
                    .toFormatter();
            formatter.parse(dateStr);
        } catch (DateTimeParseException ex) {
            // 解析 DATETIME_FORMAT1 时间格式
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }

        return LocalDateTime.parse(dateStr, formatter);
    }

/*    public static LocalDateTime parse(String str){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.parse(str,df);
    }*/

    public static String formatToLocalDate(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return df.format(localDateTime);
    }
    public static boolean compareTo(LocalDateTime dateTime1, LocalDateTime dateTime2, DateTimeFormatter formatter) {
        if (dateTime1 == null) {
            dateTime1 = LocalDateTime.now();
        }
        if (dateTime2 == null) {
            dateTime2 = LocalDateTime.now();
        }
        String date1 = format(dateTime1, formatter);
        String date2 = format(dateTime2, formatter);
        if (date1.equals(date2)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//
//        LocalDateTime parse = LocalDateTime.parse("2018-12-01 00:00:26.0", df);
//
//        String format = format(parse);
//
//        System.out.println(format);
//        System.out.println(format(LocalDateTime.now(), DateTimeFormatter.ISO_LOCAL_DATE));

        LocalDateTime localDateTime = LocalDateTime.now();

        String format = format(localDateTime);

        System.out.println(format);

    }

}
