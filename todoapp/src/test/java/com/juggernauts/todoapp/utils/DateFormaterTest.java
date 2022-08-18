package com.juggernauts.todoapp.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
}

