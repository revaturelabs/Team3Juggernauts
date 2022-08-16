package com.juggernauts.todoapp.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.MailService;
import com.juggernauts.todoapp.services.UserService;
import com.juggernauts.todoapp.web.LoginController;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters=false)
public class LoginControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private MailService mailService;

    @Before
    public void init() {
        when(mailService.sendEmail(anyString(), anyString(), anyString())).thenReturn(null);
    }

    @Test
    public void givenCorrectLoginInformation_whenLogin_thenReturnAccepted() throws Exception {
        String email = "email@test.com";
        String password = "password";
        User user = new User(password, email, false);

        when(userService.findUserByEmail(matches(email))).thenReturn(user);

        MvcResult result = mvc
            .perform(post("/login")
            .param("email", email)
            .param("password", password))
            .andExpect(status().isAccepted())
            .andReturn();

        assertEquals("You have logged in.", result.getResponse().getContentAsString());
    }

    @Test
    public void givenIncorrectLoginDetails_whenLogin_thenReturnUnauthorized() throws Exception {
        String email = "email@test.com";
        String password = "password";

        when(userService.findUserByEmail(matches(email))).thenReturn(null);

        MvcResult result = mvc
            .perform(post("/login")
            .param("email", email)
            .param("password", password))
            .andExpect(status().isUnauthorized())
            .andReturn();

        assertEquals("Invalid login details.", result.getResponse().getContentAsString());
    }

    @Test
    public void givenAlreadyLoggedIn_whenLogin_thenReturnNotAcceptable() throws Exception {
        String email = "email@test.com";
        String password = "password";
        User user = new User(password, email, false);

        MvcResult result = mvc
            .perform(post("/login")
            .sessionAttr("USER", user)
            .param("email", email)
            .param("password", password))
            .andExpect(status().isNotAcceptable())
            .andReturn();

        assertEquals("You are already logged in.", result.getResponse().getContentAsString());
    }

    @Test
    public void givenValidRegistrationInformation_whenRegister_thenReturnAccepted() throws Exception {
        String email = "email@test.com";
        String password = "password";
        User user = new User(password, email, false);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                User gotUser = (User) args[0];
                assertEquals(user.getId(), gotUser.getId());
                assertEquals(user.getPassword(), gotUser.getPassword());
                assertEquals(user.getEmail(), gotUser.getEmail());
                return null;
            }
            
        }).when(userService).addUser(any(User.class));

        MvcResult result = mvc
            .perform(post("/register")
            .param("email", email)
            .param("password", password))
            .andExpect(status().isCreated())
            .andReturn();

        assertEquals("Welcome.", result.getResponse().getContentAsString());
    }

    @Test
    public void givenEmailAlreadyTaken_whenRegister_thenReturnBadRequest() throws Exception {
        String email = "email@test.com";
        String password = "password";
        User user = new User(password, email, false);

        when(userService.findUserByEmail(email)).thenReturn(user);

        MvcResult result = mvc
            .perform(post("/register")
            .param("email", email)
            .param("password", password))
            .andExpect(status().isBadRequest())
            .andReturn();

        assertEquals("This email is taken.", result.getResponse().getContentAsString());
    }

    @Test
    public void givenUserAlreadyLoggedIn_whenRegister_thenReturnBadRequest() throws Exception {
        String email = "email@test.com";
        String password = "password";
        User user = new User(password, email, false);

        MvcResult result = mvc
            .perform(post("/register")
            .sessionAttr("USER", user)
            .param("email", email)
            .param("password", password))
            .andExpect(status().isBadRequest())
            .andReturn();

        assertEquals("You are already logged in.", result.getResponse().getContentAsString());
    }
}
