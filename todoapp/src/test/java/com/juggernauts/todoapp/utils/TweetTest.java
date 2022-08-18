package com.juggernauts.todoapp.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TweetTest {
    /**
     * Method under test: default or parameterless constructor of {@link Tweet}
     */
    @Test
    void testConstructor() {
        assertEquals("I stay on task with the ToDoApp from Habit.ual. Download the app today!", (new Tweet()).tw);
    }

    /**
     * Method under test: {@link Tweet#shareApp(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShareApp() {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        (new Tweet()).shareApp("Tw");
    }

    /**
     * Method under test: {@link Tweet#shareApp(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShareApp2() {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        (new Tweet()).shareApp("foo");
    }
}

