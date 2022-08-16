package com.juggernauts.todoapp.API_Test;

import com.juggernauts.todoapp.utils.Tweet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TweetTest {

    Tweet tw;

    @Before
    public void createNewTweetFactory() {
        Tweet tw = new Tweet();
    }


    @Test
    public void shouldSuccessfullyUpdateTwitterStatus() {
        Tweet tw = new Tweet();
        String status = "I'm the Juggernaut and I love APIs!";
        assertEquals("Successfully updated the status in Twitter.", tw.shareApp(status));

    }
}
