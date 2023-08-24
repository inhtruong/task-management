package com.cg.taskmanagement.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtils {

    public static String DMY = "dd/MM/yyyy";

    public static String formatDateString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
}
