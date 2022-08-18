package com.juggernauts.todoapp.web;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.EmailVerificationRepo;
import com.juggernauts.todoapp.repos.UserRepo;
import com.juggernauts.todoapp.services.EmailVerificationService;
import com.juggernauts.todoapp.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmailVerificationController.class, EmailVerificationService.class, UserService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class EmailVerificationControllerTest {
    @Autowired
    private EmailVerificationController emailVerificationController;

    @MockBean
    private EmailVerificationRepo emailVerificationRepo;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link EmailVerificationController#createNewEmailVerification(EmailVerification)}
     */
    @Test
    void testCreateNewEmailVerification() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(emailVerification);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(emailVerificationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link EmailVerificationController#verifyEmail(int, String)}
     */
    @Test
    void testVerifyEmail() throws Exception {
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

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");
        when(userRepo.save((User) any())).thenReturn(user2);
        when(userRepo.getReferenceById((Integer) any())).thenReturn(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/verify/{userId}/{token}", 123,
                "ABC123");
        MockMvcBuilders.standaloneSetup(emailVerificationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Your email has been verified."));
    }
}

