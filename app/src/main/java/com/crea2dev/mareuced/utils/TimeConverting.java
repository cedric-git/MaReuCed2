package com.crea2dev.mareuced.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeConverting {
    /**
     * @param dateString Date of today, for example 12:45
     * @return EPOCH
     */

    public static String getTimeFromDate(String dateString){

        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.FRENCH);
        Date date;

        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }

        return String.valueOf(date.getTime());
    }
}

