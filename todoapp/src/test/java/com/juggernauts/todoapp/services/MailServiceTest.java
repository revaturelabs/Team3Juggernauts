package com.juggernauts.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.clients.MailgunClient;
import com.juggernauts.todoapp.configurations.interceptors.services.MailService;
import com.juggernauts.todoapp.models.MailgunResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MailService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class MailServiceTest {
    @Autowired
    private MailService mailService;

    @MockBean
    private MailgunClient mailgunClient;

    /**
     * Method under test: {@link MailService#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmail() {
        MailgunResponse mailgunResponse = new MailgunResponse();
        mailgunResponse.setId("42");
        mailgunResponse.setMessage("Not all who wander are lost");
        when(mailgunClient.sendEmail((String) any(), (String) any(), (String) any(), (String) any()))
                .thenReturn(mailgunResponse);
        assertSame(mailgunResponse,
                mailService.sendEmail("alice.liddell@example.org", "Hello from the Dreaming Spires", "Text"));
        verify(mailgunClient).sendEmail((String) any(), (String) any(), (String) any(), (String) any());
    }
}

