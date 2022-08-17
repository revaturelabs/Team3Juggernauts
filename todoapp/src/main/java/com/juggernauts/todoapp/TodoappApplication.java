package com.juggernauts.todoapp;

import com.juggernauts.todoapp.utils.Tweet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.juggernauts.todoapp.models")
@EnableJpaRepositories(basePackages = "com.juggernauts.todoapp.repos")
@EnableFeignClients
public class TodoappApplication {
<<<<<<< HEAD

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);

        Tweet tweet = new Tweet();
        String tw = "Twitter test";
        tweet.shareApp(tw);

    }
=======
	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}
>>>>>>> f7791cd5abc766eb0f668e623c688b21a342ebcb
}

