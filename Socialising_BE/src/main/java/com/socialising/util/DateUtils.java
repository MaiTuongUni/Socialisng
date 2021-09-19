package com.socialising.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date convertStringToDate(String format, String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}
