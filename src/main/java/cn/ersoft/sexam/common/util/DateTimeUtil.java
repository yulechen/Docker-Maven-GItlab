package cn.ersoft.sexam.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String date2String(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    public static String date2String(Date date) {
        return new DateTime(date).toString(DEFAULT_PATTERN);
    }

    public static Date string2Date(String dateString, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(dateString).toDate();
    }

    public static Date string2Date(String dateString) {
        return DateTimeFormat.forPattern(DEFAULT_PATTERN).parseDateTime(dateString).toDate();
    }

    /**
     * 计算两个日期相隔天数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static long diffDay(Date startDate, Date endDate) {
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = (localDate2Date(end).getTime() - localDate2Date(start).getTime()) / (24 * 60 * 60 * 1000);
        return days > 0 ? days : days * -1;
    }

    private static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        Date startDate = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2018-08-01 10:22:22").toDate();
        System.out.println(diffDay(startDate, new Date()));
    }


}
