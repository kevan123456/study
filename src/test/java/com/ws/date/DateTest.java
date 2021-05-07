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
}
