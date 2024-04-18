/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangshun
 * @date 2024-04-18
 * @see
 * @since 1.0.0
 */
public class DateUtils {
    public static final String PATTERN_YMD = "yyyy-MM-dd";

    public static final String PATTERN_YMD_HMS = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_YMD_HMS2 = "yyyy/MM/dd HH:mm:ss";

    public static long parse(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
        return sdf.parse(date).getTime();
    }


    public static String formatDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMD_HMS);
        return sdf.format(date);
    }
}
