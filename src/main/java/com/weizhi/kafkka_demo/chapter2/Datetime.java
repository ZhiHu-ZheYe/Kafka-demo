package com.weizhi.kafkka_demo.chapter2;

import org.joda.time.DateTime;

import java.util.Date;

public class Datetime {

    public static void main(String[] args){
        DateTime datetime=new DateTime(new Date());
        System.out.println(datetime.toString("HH:mm:ss"));
    }
}
