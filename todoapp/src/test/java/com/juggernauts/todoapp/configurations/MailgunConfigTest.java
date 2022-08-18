package com.juggernauts.todoapp.configurations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.juggernauts.todoapp.configurations.interceptors.services.PropertiesFileLoaderService;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class MailgunConfigTest {
    @Autowired
    private MailgunConfig mailgunConfig;

    @Autowired
    private PropertiesFileLoaderService propertiesFileLoaderService;

    /**
     * Method under test: {@link MailgunConfig#mailgunContract()}
     */
    @Test
    void testMailgunContract() {
        assertTrue(mailgunConfig.mailgunContract() instanceof SpringMvcContract);
    }

    /**
     * Method under test: {@link MailgunConfig#basicAuthRequestInterceptor(PropertiesFileLoaderService)}
     */
    @Test
    void testBasicAuthRequestInterceptor() throws IOException {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by basicAuthRequestInterceptor(PropertiesFileLoaderService)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        mailgunConfig.basicAuthRequestInterceptor(propertiesFileLoaderService);
    }
}

