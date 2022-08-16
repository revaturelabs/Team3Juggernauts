package com.juggernauts.todoapp.utils;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * git branch: Twitter_API
 *
 * Twitter4J/ wrapper for Twitter API V2
 * Tweet object POSTs tweet to share ToDoApp
 * for Team Juggernaut, by way of Habit.ual
 */

public class Tweet {

    public void shareApp(){

        System.out.println("Twitter test in progress");

        String API_KEY = "Dp95YCqtDblOcVn5SeJnNfXxA";
        String API_KEY_SECRET ="4R4WllVJCiENgNOg7Bcv7uhhGyMjEN6PnTSEwUhaZxqDKmbzbb";
        String ACCESS_TOKEN = "1559535790501335041-YWGK7A1XZpflnQMBGHlH2Nyk8gLgIn";
        String ACCESS_TOKEN_SECRET = "5X7aZkzWitKc06NaUdUN64xlalnPQxxHWT81gjfsdfq3G";
        String tweet = "I stay on task with the ToDoApp from Habit.ual. Download their app today!!";

        try {
            twitter4j.Twitter twitter = new TwitterFactory().getInstance();

            // twitter.setOAuthConsumer(API_KEY, API_KEY_SECRET);

            AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

            twitter.setOAuthAccessToken(accessToken);

            twitter.updateStatus("Successfully tweeted from Java Twitter4J API!!!");

            System.out.println("Successfully updated the status in Twitter.");
        } catch (TwitterException te) {
            te.printStackTrace();
        }
    }
    }
