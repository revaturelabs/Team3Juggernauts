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

        String API_KEY = "x";
        String API_KEY_SECRET ="x";
        String ACCESS_TOKEN = "x";
        String ACCESS_TOKEN_SECRET = "x";
        String tweet = "I stay on task with the ToDoApp from Habit.ual. Download their app today!!";

        try {
            twitter4j.Twitter twitter = new TwitterFactory().getInstance();

            // twitter.setOAuthConsumer(API_KEY, API_KEY_SECRET);

            AccessToken accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);

            twitter.setOAuthAccessToken(accessToken);

            twitter.updateStatus(tweet);

            System.out.println("Successfully updated the status in Twitter.");
        } catch (TwitterException te) {
            te.printStackTrace();
        }
    }
    }
