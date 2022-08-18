package com.juggernauts.todoapp.web;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juggernauts.todoapp.configurations.interceptors.services.TaskService;
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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

@ContextConfiguration(classes = {TaskController.class, TaskService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class TaskControllerTest {
    @Autowired
    private TaskController taskController;

    @MockBean
    private TaskRepo taskRepo;

    /**
     * Method under test: {@link TaskController#changeCategory(Task)}
     */
    @Test
    void testChangeCategory() throws Exception {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("Category Name");
        category.setTasks(new ArrayList<>());
        category.setUser(user);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");

        Task task = new Task();
        task.setCategory(category);
        task.setCompleteBy(LocalDate.ofEpochDay(1L));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("Name");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepo.findById((Integer) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("Category Name");
        category1.setTasks(new ArrayList<>());
        category1.setUser(user2);

        User user3 = new User();
        user3.setCategories(new ArrayList<>());
        user3.setEmail("jane.doe@example.org");
        user3.setEmailVerified(true);
        user3.setId(1);
        user3.setPassword("iloveyou");

        Task task1 = new Task();
        task1.setCategory(category1);
        task1.setCompleteBy(null);
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("Name");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);
        String content = (new ObjectMapper()).writeValueAsString(task1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"taskId\":123,\"name\":\"Name\",\"completeBy\":null,\"description\":\"The characteristics of someone or"
                                        + " something\",\"repeatEvery\":1,\"user\":{\"id\":1,\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\","
                                        + "\"emailVerified\":true,\"categories\":[]},\"category\":{\"categoryId\":123,\"categoryName\":\"Category"
                                        + " Name\"},\"reminders\":[],\"done\":true}"));
    }

    /**
     * Method under test: {@link TaskController#changeCategory(Task)}
     */
    @Test
    void testChangeCategory2() throws Exception {
        when(taskRepo.findById((Integer) any())).thenReturn(Optional.empty());

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("Category Name");
        category.setTasks(new ArrayList<>());
        category.setUser(user);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");

        Task task = new Task();
        task.setCategory(category);
        task.setCompleteBy(null);
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("Name");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);
        String content = (new ObjectMapper()).writeValueAsString(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("123"));
    }
}

