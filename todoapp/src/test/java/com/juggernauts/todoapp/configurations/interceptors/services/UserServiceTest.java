package com.juggernauts.todoapp.configurations.interceptors.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
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

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser3() {
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

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("User(id=");
        category1.setTasks(new ArrayList<>());
        category1.setUser(user2);

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);

        User user3 = new User();
        user3.setCategories(categoryList);
        user3.setEmail("jane.doe@example.org");
        user3.setEmailVerified(true);
        user3.setId(1);
        user3.setPassword("iloveyou");
        assertEquals("User(id=1, password=iloveyou, email=jane.doe@example.org, emailVerified=true, categories=[Category"
                + "{categoryId=123, categoryName='User(id=', user=User(id=1, password=iloveyou, email=jane.doe@example.org,"
                + " emailVerified=true, categories=[]), tasks=[]}, Category{categoryId=123, categoryName='User(id=',"
                + " user=User(id=1, password=iloveyou, email=jane.doe@example.org, emailVerified=true, categories=[]),"
                + " tasks=[]}])", userService.addUser(user3));
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser4() {
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

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Task task = new Task();
        task.setCategory(category);
        task.setCompleteBy(LocalDate.ofEpochDay(3L));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("User(id=");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(3);
        task.setTaskId(123);
        task.setUser(user2);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);

        User user3 = new User();
        user3.setCategories(new ArrayList<>());
        user3.setEmail("jane.doe@example.org");
        user3.setEmailVerified(true);
        user3.setId(1);
        user3.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("User(id=");
        category1.setTasks(taskList);
        category1.setUser(user3);

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);

        User user4 = new User();
        user4.setCategories(categoryList);
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerified(true);
        user4.setId(1);
        user4.setPassword("iloveyou");
        assertEquals("User(id=1, password=iloveyou, email=jane.doe@example.org, emailVerified=true, categories=[Category"
                + "{categoryId=123, categoryName='User(id=', user=User(id=1, password=iloveyou, email=jane.doe@example.org,"
                + " emailVerified=true, categories=[]), tasks=[{NAME: User(id=, UserID: 1}]}])", userService.addUser(user4));
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserService#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(userList);
        List<User> actualAllUsers = userService.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserService#getUser(int)}
     */
    @Test
    void testGetUser() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        when(userRepo.getReferenceById((Integer) any())).thenReturn(user);
        assertSame(user, userService.getUser(1));
        verify(userRepo).getReferenceById((Integer) any());
    }

    /**
     * Method under test: {@link UserService#getUser(User)}
     */
    @Test
    void testGetUser2() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findOne((Example<User>) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");
        Optional<User> actualUser = userService.getUser(user1);
        assertSame(ofResult, actualUser);
        assertTrue(actualUser.isPresent());
        verify(userRepo).findOne((Example<User>) any());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    void testGetUser3() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        when(userRepo.findOne((String) any())).thenReturn(user);
        assertSame(user, userService.getUser("jane.doe@example.org"));
        verify(userRepo).findOne((String) any());
    }

    /**
     * Method under test: {@link UserService#verifyEmail(User)}
     */
    @Test
    void testVerifyEmail() {
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
        userService.verifyEmail(user1);
        verify(userRepo).save((User) any());
        assertTrue(user1.isEmailVerified());
    }
}

