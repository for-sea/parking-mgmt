package com.forsea;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

@Slf4j
@SpringBootTest
public class TimeTest {


    @Test
    void testTimeCal() {
        String today = DateUtil.today();
        DateTime date = DateUtil.date();
        String time = DateUtil.formatTime(date);
        /*计算时间差*/
        String dateStr1 = "2017-03-01 00:00:00";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-03-01 01:25:00";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long between = DateUtil.between(date1, date2, DateUnit.MINUTE);
        float hour = (float) between / 60 - 1;
        Float betweenHour = Math.round(hour * 100) / 100f;

        //Level.MINUTE表示精确到分
//        String formatBetween = DateUtil.formatBetween(between, BetweenFormatter.Level.SECOND);
        log.info("相差时间: {}", between);
        log.info("相差小时: {}", betweenHour);
        log.info("当前日期: {}", today);
        log.info("当前时间: {}", time);


        log.info("===================================");
        DateTime date3 = DateUtil.date();
        String formatDateTime = DateUtil.formatDateTime(date3);
        log.info("date2: =======> {}", formatDateTime);
    }


    private static Object resourceA = new Object();

    @Test
    void testReverseString() throws InterruptedException {
        ThreadLocal<String> name = new ThreadLocal<>();

        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        synchronized (resourceA) {

                            System.out.println("t1" + name.get());
                            name.set("shabi");
                            System.out.println("t1" + name.get());

                        }
                    }
                }
        );

        Thread thread2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        synchronized (resourceA) {

                                System.out.println("t2"+name.get());
                                name.set("niubi");
                                System.out.println("t2"+name.get());

                        }
                    }
                }
        );
        thread1.start();
        Thread.sleep(1000);
        thread2.start();




    }
}
