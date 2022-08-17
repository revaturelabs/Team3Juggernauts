package com.juggernauts.todoapp.web;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.clients.MailgunClient;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.EmailVerificationRepo;
import com.juggernauts.todoapp.repos.UserRepo;
import com.juggernauts.todoapp.services.EmailVerificationService;
import com.juggernauts.todoapp.services.MailService;
import com.juggernauts.todoapp.services.TokenGenerationService;
import com.juggernauts.todoapp.services.UserService;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LoginController.class, EmailVerificationService.class, MailService.class,
        TokenGenerationService.class, UserService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class LoginControllerTest {
    @MockBean
    private EmailVerificationRepo emailVerificationRepo;

    @Autowired
    private LoginController loginController;

    @MockBean
    private MailgunClient mailgunClient;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link LoginController#login(String, String, HttpServletRequest)}
     */
    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        when(userRepo.findOne((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .param("email", "foo")
                .param("password", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Invalid login details."));
    }

    /**
     * Method under test: {@link LoginController#login(String, String, HttpServletRequest)}
     */
    @Test
    void testLogin2() throws Exception {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("foo");
        when(userRepo.findOne((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .param("email", "foo")
                .param("password", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("You have logged in."));
    }
}

