package com.ws.date;

import junit.framework.TestCase;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yunhua
 * @since 2020-10-20
 */
public class DateTest extends TestCase {


    @Test
    public void testAddYear() {
        Date now = new Date();
        Date endTime = DateUtils.addYears(now, 1);
        System.out.println(endTime);
    }

    @Test
    public void testFormatDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sdf.format(date);
        System.out.println(d);
    }

    @Test
    public void test() {
        long now = System.currentTimeMillis();
        long time = now - 1603174200000L;
        System.out.println("m:" + time);
        System.out.println("day:" + time / 24 / 3600 / 1000);

        long start = now - (365L * 24 * 60 * 60 * 1000);

        System.out.println("start:" + start);
    }
}
