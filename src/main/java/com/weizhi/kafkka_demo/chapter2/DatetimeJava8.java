package com.weizhi.kafkka_demo.chapter2;

import org.apache.tomcat.jni.Local;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.time.*;

public class DatetimeJava8 {
    public static void main(String[] args){
        LocalDate localDate=LocalDate.now();
        localDate=LocalDate.of(2020,04,4);
        localDate= LocalDate.MAX;
        localDate= LocalDate.ofEpochDay(11111111);
        localDate=LocalDate.ofYearDay(2020,20);
        System.out.println("localDate:"+localDate);

        LocalDate localDate1=LocalDate.now();
        System.out.println("DayOfWeek:"+localDate1.getDayOfWeek()
                +",year:"+localDate1.getYear()
                +",Month:"+localDate1.getMonthValue()
                +",Day:"+localDate1.getDayOfMonth()
                +",Month:"+localDate1.getMonth()
                +",DayOfYear:"+localDate1.getDayOfYear());
        LocalDate localDate2=LocalDate.now();
        LocalDate localDate3=localDate.of(2020,03,10);
        System.out.println("判断日期相等："+localDate2.equals(localDate3));

        //判断生日
        LocalDate localDate4=LocalDate.now();
        LocalDate localDate5=LocalDate.of(2019,03,10);
        MonthDay birthDay=MonthDay.of(localDate4.getMonth(),localDate4.getDayOfMonth());
        MonthDay nowDate=MonthDay.from(localDate5);
        if(birthDay.equals(nowDate)){
            System.out.println("今天是生日");
        }else{
            System.out.println("今天不是生日");
        }

        LocalTime time=LocalTime.now();
        time=time.minusHours(1);
        LocalDateTime localDateTime=time.atDate(LocalDate.now());
        System.out.println("time:"+time+",localDateTime:"+localDateTime);
        int i=time.compareTo(LocalTime.now());
        int nano=time.getNano();
        boolean f=time.isAfter(LocalTime.of(12,21));
        System.out.println("i:"+i+",nano:"+nano+",f:"+f);
    }
}
