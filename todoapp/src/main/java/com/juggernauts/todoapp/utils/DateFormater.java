package com.juggernauts.todoapp.utils;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormater {

    static final int DAY = 60 * 60 * 24;
    static final int WEEK = DAY * 7;
    static final int MONTH = DAY * 30;
    static final int YEAR = DAY * 365;

    public static LocalDate getDateObject(String dateString) throws DateTimeParseException {

        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

        return localDate;
    }

    public static LocalDateTime convertToDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.NOON);
    }

    public static LocalDate addToDate(LocalDate originalDate, int seconds) {
        long numDays;
        if (seconds < 86400) {
            numDays = 1;
        } else {
            numDays = seconds / DAY;
        }
        return originalDate.plusDays(numDays);
    }



}
