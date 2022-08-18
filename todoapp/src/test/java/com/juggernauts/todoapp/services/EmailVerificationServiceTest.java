package com.juggernauts.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.configurations.interceptors.services.EmailVerificationService;
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.EmailVerificationRepo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailVerificationService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class EmailVerificationServiceTest {
    @MockBean
    private EmailVerificationRepo emailVerificationRepo;

    @Autowired
    private EmailVerificationService emailVerificationService;

    /**
     * Method under test: {@link EmailVerificationService#addEmailVerification(EmailVerification)}
     */
    @Test
    void testAddEmailVerification() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setToken("ABC123");
        emailVerification.setTokenId(123);
        emailVerification.setUser(user);
        when(emailVerificationRepo.save((EmailVerification) any())).thenReturn(emailVerification);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");

        EmailVerification emailVerification1 = new EmailVerification();
        emailVerification1.setToken("ABC123");
        emailVerification1.setTokenId(123);
        emailVerification1.setUser(user1);
        assertEquals(
                "EmailVerification(tokenId=123, token=ABC123, user=User(id=1, password=iloveyou, email=jane.doe@example.org,"
                        + " emailVerified=true, categories=[]))",
                emailVerificationService.addEmailVerification(emailVerification1));
        verify(emailVerificationRepo).save((EmailVerification) any());
    }

    /**
     * Method under test: {@link EmailVerificationService#getEmailVerification(User, String)}
     */
    @Test
    void testGetEmailVerification() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setToken("ABC123");
        emailVerification.setTokenId(123);
        emailVerification.setUser(user);
        when(emailVerificationRepo.findOne((User) any(), (String) any())).thenReturn(emailVerification);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");
        assertSame(emailVerification, emailVerificationService.getEmailVerification(user1, "ABC123"));
        verify(emailVerificationRepo).findOne((User) any(), (String) any());
    }

    /**
     * Method under test: {@link EmailVerificationService#deleteEmailVerification(EmailVerification)}
     */
    @Test
    void testDeleteEmailVerification() {
        doNothing().when(emailVerificationRepo).delete((EmailVerification) any());

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setToken("ABC123");
        emailVerification.setTokenId(123);
        emailVerification.setUser(user);
        emailVerificationService.deleteEmailVerification(emailVerification);
        verify(emailVerificationRepo).delete((EmailVerification) any());
        assertEquals("ABC123", emailVerification.getToken());
        assertSame(user, emailVerification.getUser());
        assertEquals(123, emailVerification.getTokenId());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link EmailVerificationService}
     *   <li>{@link EmailVerificationService#setEmailVerificationRepo(EmailVerificationRepo)}
     * </ul>
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     EmailVerificationService.emailVerificationRepo

        (new EmailVerificationService()).setEmailVerificationRepo(mock(EmailVerificationRepo.class));
    }
}

