package com.spring.boot.action.aop.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 *
 * Created by fgm on 2018/2/24.
 *
 * 在Java 8中，日期和时间被明确划分为LocalDate和LocalTime，
 * LocalDate无法包含时间，
 * LocalTime无法包含日期。
 * LocalDateTime才能同时包含日期和时间。
 *
 * JDBC 对应 Java格式时间
 * SQL -> Java
 *--------------------------
 *date -> LocalDate
 *time -> LocalTime
 *timestamp -> LocalDateTime
 *
 */
public class DateUtil {


    /**
     * LocalDate
     * @param args
     */
    public static void main(String[] args) {
        localDate();
        localTime();
        localDateTime();

    }

    private static void localDateTime() {




    }

    public static  void localDate(){
        System.out.println("----------localDate----------");

        //获取当前时间
        LocalDate today=LocalDate.now();
        System.out.println("today:"+today);

        //设置时间
        LocalDate localDate= LocalDate.of(2018,02,24);
        System.out.println("localDate:"+localDate);

        int month=localDate.getMonthValue();
        int year=localDate.getYear();
        int dayOfMonth=localDate.getDayOfMonth();
        int dayOfYear=localDate.getDayOfYear();

        System.out.println("year:"+year);
        System.out.println("month:"+month);
        System.out.println("dayOfMonth:"+dayOfMonth);
        System.out.println("dayOfYear:"+dayOfYear);
        //字符和日期转换
        LocalDate parseDate=LocalDate.parse("2018-10-31");
        System.out.println("parseDate:"+parseDate);
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfThisMonth:"+firstDayOfThisMonth);
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println("secondDayOfThisMonth:"+secondDayOfThisMonth);
       // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfThisMonth:"+lastDayOfThisMonth);
        // 取下一天：
        LocalDate nextDay = today.plusDays(1);
        System.out.println("nextDay:"+nextDay);
        //下个月第一天，不用计算这个月啥时候结束
        LocalDate nextMonthFirstDay= lastDayOfThisMonth.plusDays(1);
        System.out.println("nextMonthFirstDay:"+nextMonthFirstDay);
        // 取2018年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMonthOfMonday2018=LocalDate.parse("2018-01-31").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println("firstMonthOfMonday2018:"+firstMonthOfMonday2018);
        System.out.println("----------localDate----------");

    }

    public static  void localTime(){
        System.out.println("\n\n\n----------localTime----------");

        //LocalTime包含毫秒
        LocalTime time= LocalTime.now();
        System.out.println("time:"+time);
        //你可能想清除毫秒数
        LocalTime withOutNanoTime= LocalTime.now().withNano(0);
        System.out.println("withOutNanoTime:"+withOutNanoTime);

        //构造时间
        LocalTime parseTime= LocalTime.parse("12:00:01");
        System.out.println("parseTime:"+parseTime);
        LocalTime zero = LocalTime.of(0, 0, 0);
        System.out.println("zeroTime:"+zero);


        System.out.println("----------localTime----------");

    }





}
