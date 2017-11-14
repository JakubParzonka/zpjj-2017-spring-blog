package edu.wat.pl.blog.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dtf.format(LocalDate.now());
    }

}
