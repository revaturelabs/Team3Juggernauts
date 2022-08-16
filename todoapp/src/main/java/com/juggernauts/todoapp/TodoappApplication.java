package com.juggernauts.todoapp;

import com.juggernauts.todoapp.utils.Tweet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);

        Tweet tweet = new Tweet();
        String tw = "Twitter test";
        tweet.shareApp(tw);

    }
}

