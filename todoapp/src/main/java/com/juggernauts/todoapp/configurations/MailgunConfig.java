package com.juggernauts.todoapp.configurations;

import java.io.IOException;
import java.util.Properties;

import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.juggernauts.todoapp.services.PropertiesFileLoaderService;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * A configuration for the Mailgun FeignClient
 */
@Configuration
public class MailgunConfig {
    /**
     * Sets the contract the client uses to a SpringMvcContract
     * This is so we can use Spring MVC RequestMappings for requests
     * @return A SpringMvcContract
     */
    @Bean
    public Contract mailgunContract() {
        return new SpringMvcContract();
    }

    /**
     * Sets up the authentication for the Mailgun API with our username and password
     * @param props The service for handling the loading of properties files
     * @return A Basic authentication interceptor for all Mailgun requests
     * @throws IOException
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(PropertiesFileLoaderService props) throws IOException {
        Properties properties = props.getProperties(new ClassPathResource("secrets.properties"));
        return new BasicAuthRequestInterceptor(
            properties.getProperty("mailgun.user.username", ""), 
            properties.getProperty("mailgun.user.password", "")
        );
    }
}