package com.juggernauts.todoapp.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class DateFormaterTest {
    /**
     * Method under test: {@link DateFormater#getDateObject(String)}
     */
    @Test
    void testGetDateObject() {
        assertEquals("2020-03-01", DateFormater.getDateObject("2020-03-01").toString());
    }

    /**
     * Method under test: {@link DateFormater#getDateObject(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDateObject2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.time.format.DateTimeParseException: Text 'foo' could not be parsed at index 0
        //       at java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:1949)
        //       at java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1851)
        //       at java.time.LocalDate.parse(LocalDate.java:400)
        //       at com.juggernauts.todoapp.utils.DateFormater.getDateObject(DateFormater.java:12)
        //   In order to prevent getDateObject(String)
        //   from throwing DateTimeParseException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getDateObject(String).
        //   See https://diff.blue/R013 to resolve this issue.

        DateFormater.getDateObject("foo");
    }

    /**
     * Method under test: {@link DateFormater#convertToDateTime(LocalDate)}
     */
    @Test
    void testConvertToDateTime() {
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        LocalDateTime actualConvertToDateTimeResult = DateFormater.convertToDateTime(ofEpochDayResult);
        LocalDate toLocalDateResult = actualConvertToDateTimeResult.toLocalDate();
        assertSame(ofEpochDayResult, toLocalDateResult);
        assertEquals("1970-01-02", toLocalDateResult.toString());
        assertEquals("12:00", actualConvertToDateTimeResult.toLocalTime().toString());
    }

    /**
     * Method under test: {@link DateFormater#convertToDateTime(LocalDate)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConvertToDateTime2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: date
        //       at java.util.Objects.requireNonNull(Objects.java:228)
        //       at java.time.LocalDateTime.of(LocalDateTime.java:374)
        //       at com.juggernauts.todoapp.utils.DateFormater.convertToDateTime(DateFormater.java:19)
        //   In order to prevent convertToDateTime(LocalDate)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   convertToDateTime(LocalDate).
        //   See https://diff.blue/R013 to resolve this issue.

        DateFormater.convertToDateTime(null);
    }

    /**
     * Method under test: {@link DateFormater#addToDate(LocalDate, int)}
     */
    @Test
    void testAddToDate() {
        assertEquals("1970-01-03", DateFormater.addToDate(LocalDate.ofEpochDay(1L), 2).toString());
        assertEquals("1970-01-03", DateFormater.addToDate(LocalDate.ofEpochDay(1L), 86400).toString());
    }

    /**
     * Method under test: {@link DateFormater#addToDate(LocalDate, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddToDate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.utils.DateFormater.addToDate(DateFormater.java:36)
        //   In order to prevent addToDate(LocalDate, int)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addToDate(LocalDate, int).
        //   See https://diff.blue/R013 to resolve this issue.

        DateFormater.addToDate(null, 2);
    }
}

