package com.juggernauts.todoapp.configurations.interceptors.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        when(userRepo.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");
        assertEquals("User(id=1, password=iloveyou, email=jane.doe@example.org, emailVerified=true, categories=[])",
                userService.addUser(user1));
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser2() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        when(userRepo.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("User(id=");
        category.setTasks(new ArrayList<>());
        category.setUser(user1);

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        User user2 = new User();
        user2.setCategories(categoryList);
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");
        assertEquals("User(id=1, password=iloveyou, email=jane.doe@example.org, emailVerified=true, categories=[Category"
                + "{categoryId=123, categoryName='User(id=', user=User(id=1, password=iloveyou, email=jane.doe@example.org,"
                + " emailVerified=true, categories=[]), tasks=[]}])", userService.addUser(user2));
        verify(userRepo).save((User) any());
    }
}

