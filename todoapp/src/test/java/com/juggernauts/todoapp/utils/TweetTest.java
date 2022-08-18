package com.juggernauts.todoapp.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TweetTest {
    /**
     * Method under test: default or parameterless constructor of {@link Tweet}
     */
    @Test
    void testConstructor() {
        assertEquals("I stay on task with the ToDoApp from Habit.ual. Download the app today!", (new Tweet()).tw);
    }
}

