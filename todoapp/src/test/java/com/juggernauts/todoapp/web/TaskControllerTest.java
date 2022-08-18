package com.juggernauts.todoapp.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juggernauts.todoapp.configurations.interceptors.services.TaskService;
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
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
     * Method under test: {@link TaskController#createNewTask(Task, HttpServletRequest)}
     */
    @Test
    void testCreateNewTask() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class javax.servlet.http.HttpServletRequest]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `javax.servlet.http.HttpServletRequest` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 1]
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:388)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:343)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:185)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:160)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:133)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:122)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:179)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:146)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:199)
        //   In order to prevent createNewTask(Task, HttpServletRequest)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createNewTask(Task, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        TaskController taskController = new TaskController(new TaskService(mock(TaskRepo.class)));

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
        ResponseEntity actualCreateNewTaskResult = taskController.createNewTask(task, new MockHttpServletRequest());
        assertEquals("Task added successfully", actualCreateNewTaskResult.getBody());
        assertEquals(HttpStatus.OK, actualCreateNewTaskResult.getStatusCode());
        assertTrue(actualCreateNewTaskResult.getHeaders().isEmpty());
        assertNull(task.getUser());
    }

    /**
     * Method under test: {@link TaskController#createNewTask(Task, HttpServletRequest)}
     */
    @Test
    void testCreateNewTask2() throws RuntimeException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class javax.servlet.http.HttpServletRequest]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `javax.servlet.http.HttpServletRequest` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 1]
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:388)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:343)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:185)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:160)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:133)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:122)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:179)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:146)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:199)
        //   In order to prevent createNewTask(Task, HttpServletRequest)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createNewTask(Task, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

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
        TaskRepo taskRepo = mock(TaskRepo.class);
        when(taskRepo.save((Task) any())).thenReturn(task);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("USER");
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
        task1.setCompleteBy(LocalDate.ofEpochDay(1L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("USER");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);

        TaskService taskService = new TaskService(taskRepo);
        taskService.addTask(task1);
        TaskController taskController = new TaskController(taskService);

        User user4 = new User();
        user4.setCategories(new ArrayList<>());
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerified(true);
        user4.setId(1);
        user4.setPassword("iloveyou");

        Category category2 = new Category();
        category2.setCategoryId(123);
        category2.setCategoryName("Category Name");
        category2.setTasks(new ArrayList<>());
        category2.setUser(user4);

        User user5 = new User();
        user5.setCategories(new ArrayList<>());
        user5.setEmail("jane.doe@example.org");
        user5.setEmailVerified(true);
        user5.setId(1);
        user5.setPassword("iloveyou");

        Task task2 = new Task();
        task2.setCategory(category2);
        task2.setCompleteBy(LocalDate.ofEpochDay(1L));
        task2.setDescription("The characteristics of someone or something");
        task2.setDone(true);
        task2.setName("Name");
        task2.setReminders(new ArrayList<>());
        task2.setRepeatEvery(1);
        task2.setTaskId(123);
        task2.setUser(user5);
        ResponseEntity actualCreateNewTaskResult = taskController.createNewTask(task2, new MockHttpServletRequest());
        assertEquals("Task added successfully", actualCreateNewTaskResult.getBody());
        assertEquals(HttpStatus.OK, actualCreateNewTaskResult.getStatusCode());
        assertTrue(actualCreateNewTaskResult.getHeaders().isEmpty());
        verify(taskRepo).save((Task) any());
        assertNull(task2.getUser());
    }

    /**
     * Method under test: {@link TaskController#createNewTask(Task, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewTask3() throws RuntimeException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class javax.servlet.http.HttpServletRequest]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `javax.servlet.http.HttpServletRequest` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 1]
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:388)
        //       at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:343)
        //       at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:185)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:160)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:133)
        //       at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:122)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:179)
        //       at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:146)
        //       at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
        //       at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)
        //       at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //       at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1070)
        //       at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)
        //       at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
        //       at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:655)
        //       at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
        //       at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:72)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //       at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
        //       at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
        //       at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:199)
        //   In order to prevent createNewTask(Task, HttpServletRequest)
        //   from throwing HttpMessageConversionException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createNewTask(Task, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.web.TaskController.createNewTask(TaskController.java:34)
        //   In order to prevent createNewTask(Task, HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createNewTask(Task, HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

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
        TaskRepo taskRepo = mock(TaskRepo.class);
        when(taskRepo.save((Task) any())).thenReturn(task);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("USER");
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
        task1.setCompleteBy(LocalDate.ofEpochDay(1L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("USER");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);

        TaskService taskService = new TaskService(taskRepo);
        taskService.addTask(task1);
        TaskController taskController = new TaskController(taskService);

        User user4 = new User();
        user4.setCategories(new ArrayList<>());
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerified(true);
        user4.setId(1);
        user4.setPassword("iloveyou");

        Category category2 = new Category();
        category2.setCategoryId(123);
        category2.setCategoryName("Category Name");
        category2.setTasks(new ArrayList<>());
        category2.setUser(user4);

        User user5 = new User();
        user5.setCategories(new ArrayList<>());
        user5.setEmail("jane.doe@example.org");
        user5.setEmailVerified(true);
        user5.setId(1);
        user5.setPassword("iloveyou");

        Task task2 = new Task();
        task2.setCategory(category2);
        task2.setCompleteBy(LocalDate.ofEpochDay(1L));
        task2.setDescription("The characteristics of someone or something");
        task2.setDone(true);
        task2.setName("Name");
        task2.setReminders(new ArrayList<>());
        task2.setRepeatEvery(1);
        task2.setTaskId(123);
        task2.setUser(user5);
        taskController.createNewTask(task2, null);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    void testViewAllTasks() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        TaskRepo taskRepo = mock(TaskRepo.class);
        when(taskRepo.findAll()).thenReturn(new ArrayList<>());
        TaskController taskController = new TaskController(new TaskService(taskRepo));
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        ResponseEntity actualViewAllTasksResult = taskController.viewAllTasks(mockHttpServletRequest);
        assertEquals("No tasks found.", actualViewAllTasksResult.getBody());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, actualViewAllTasksResult.getStatusCode());
        assertTrue(actualViewAllTasksResult.getHeaders().isEmpty());
        verify(taskRepo).findAll();
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasks2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.lambda$getTasks$0(TaskService.java:35)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:174)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1384)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.getTasks(TaskService.java:36)
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasks(TaskController.java:55)
        //   In order to prevent viewAllTasks(HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasks(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("USER");
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
        task.setName("USER");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        TaskRepo taskRepo = mock(TaskRepo.class);
        when(taskRepo.findAll()).thenReturn(taskList);
        TaskController taskController = new TaskController(new TaskService(taskRepo));
        taskController.viewAllTasks(new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    void testViewAllTasks3() throws NoSuchElementException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        TaskService taskService = mock(TaskService.class);
        when(taskService.getTasks((User) any())).thenReturn(new ArrayList<>());
        TaskController taskController = new TaskController(taskService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        ResponseEntity actualViewAllTasksResult = taskController.viewAllTasks(mockHttpServletRequest);
        assertEquals("[]", actualViewAllTasksResult.getBody());
        assertEquals(HttpStatus.OK, actualViewAllTasksResult.getStatusCode());
        assertTrue(actualViewAllTasksResult.getHeaders().isEmpty());
        verify(taskService).getTasks((User) any());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    void testViewAllTasks4() throws NoSuchElementException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("USER");
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
        task.setName("USER");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        TaskService taskService = mock(TaskService.class);
        when(taskService.getTasks((User) any())).thenReturn(taskList);
        TaskController taskController = new TaskController(taskService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        ResponseEntity actualViewAllTasksResult = taskController.viewAllTasks(mockHttpServletRequest);
        assertEquals("An error occured.", actualViewAllTasksResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualViewAllTasksResult.getStatusCode());
        assertTrue(actualViewAllTasksResult.getHeaders().isEmpty());
        verify(taskService).getTasks((User) any());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    void testViewAllTasks5() throws NoSuchElementException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("USER");
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
        task.setName("USER");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("USER");
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
        task1.setCompleteBy(LocalDate.ofEpochDay(1L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("USER");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        TaskService taskService = mock(TaskService.class);
        when(taskService.getTasks((User) any())).thenReturn(taskList);
        TaskController taskController = new TaskController(taskService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        ResponseEntity actualViewAllTasksResult = taskController.viewAllTasks(mockHttpServletRequest);
        assertEquals("An error occured.", actualViewAllTasksResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualViewAllTasksResult.getStatusCode());
        assertTrue(actualViewAllTasksResult.getHeaders().isEmpty());
        verify(taskService).getTasks((User) any());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasks6() throws NoSuchElementException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasks(TaskController.java:51)
        //   In order to prevent viewAllTasks(HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasks(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        TaskService taskService = mock(TaskService.class);
        when(taskService.getTasks((User) any())).thenReturn(new ArrayList<>());
        (new TaskController(taskService)).viewAllTasks(null);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasks7() throws NoSuchElementException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasks(TaskController.java:51)
        //   In order to prevent viewAllTasks(HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasks(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        TaskService taskService = mock(TaskService.class);
        when(taskService.getTasks((User) any())).thenThrow(new NoSuchElementException("USER"));
        TaskController taskController = new TaskController(taskService);
        taskController.viewAllTasks(new Request(new Connector()));
    }

    /**
     * Method under test: {@link TaskController#viewAllTasks(HttpServletRequest)}
     */
    @Test
    void testViewAllTasks8() throws NoSuchElementException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("USER");
        category.setTasks(new ArrayList<>());
        category.setUser(user);

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
        Task task = mock(Task.class);
        when(task.getCategory()).thenReturn(category1);
        when(task.getUser()).thenReturn(user3);
        when(task.getRepeatEvery()).thenReturn(1);
        when(task.getTaskId()).thenReturn(123);
        when(task.getDescription()).thenReturn("The characteristics of someone or something");
        when(task.getName()).thenReturn("Name");
        when(task.getCompleteBy()).thenReturn(null);
        when(task.getReminders()).thenReturn(new ArrayList<>());
        doNothing().when(task).setCategory((Category) any());
        doNothing().when(task).setCompleteBy((LocalDate) any());
        doNothing().when(task).setDescription((String) any());
        doNothing().when(task).setDone(anyBoolean());
        doNothing().when(task).setName((String) any());
        doNothing().when(task).setReminders((List<Reminder>) any());
        doNothing().when(task).setRepeatEvery(anyInt());
        doNothing().when(task).setTaskId(anyInt());
        doNothing().when(task).setUser((User) any());
        task.setCategory(category);
        task.setCompleteBy(LocalDate.ofEpochDay(1L));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("USER");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        TaskService taskService = mock(TaskService.class);
        when(taskService.getTasks((User) any())).thenReturn(taskList);
        TaskController taskController = new TaskController(taskService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        ResponseEntity actualViewAllTasksResult = taskController.viewAllTasks(mockHttpServletRequest);
        assertEquals("An error occured.", actualViewAllTasksResult.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualViewAllTasksResult.getStatusCode());
        assertTrue(actualViewAllTasksResult.getHeaders().isEmpty());
        verify(taskService).getTasks((User) any());
        verify(task).getCategory();
        verify(task).getUser();
        verify(task).getRepeatEvery();
        verify(task).getTaskId();
        verify(task).getDescription();
        verify(task).getName();
        verify(task).getCompleteBy();
        verify(task).getReminders();
        verify(task).setCategory((Category) any());
        verify(task).setCompleteBy((LocalDate) any());
        verify(task).setDescription((String) any());
        verify(task).setDone(anyBoolean());
        verify(task).setName((String) any());
        verify(task).setReminders((List<Reminder>) any());
        verify(task).setRepeatEvery(anyInt());
        verify(task).setTaskId(anyInt());
        verify(task).setUser((User) any());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasksByCategory(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasksByCategory() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.getTasks(TaskService.java:38)
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.getAllTasksPerCategory(TaskService.java:47)
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasksByCategory(TaskController.java:73)
        //   In order to prevent viewAllTasksByCategory(HttpServletRequest)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasksByCategory(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        TaskRepo taskRepo = mock(TaskRepo.class);
        when(taskRepo.findAll()).thenReturn(new ArrayList<>());
        TaskController taskController = new TaskController(new TaskService(taskRepo));
        taskController.viewAllTasksByCategory(new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link TaskController#viewAllTasksByCategory(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasksByCategory2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.lambda$getTasks$0(TaskService.java:35)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:174)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1384)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.getTasks(TaskService.java:36)
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.getAllTasksPerCategory(TaskService.java:47)
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasksByCategory(TaskController.java:73)
        //   In order to prevent viewAllTasksByCategory(HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasksByCategory(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("USER");
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
        task.setName("USER");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        TaskRepo taskRepo = mock(TaskRepo.class);
        when(taskRepo.findAll()).thenReturn(taskList);
        TaskController taskController = new TaskController(new TaskService(taskRepo));
        taskController.viewAllTasksByCategory(new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link TaskController#viewAllTasksByCategory(HttpServletRequest)}
     */
    @Test
    void testViewAllTasksByCategory3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        TaskService taskService = mock(TaskService.class);
        when(taskService.getAllTasksPerCategory((User) any())).thenReturn(new HashMap<>());
        TaskController taskController = new TaskController(taskService);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        ResponseEntity actualViewAllTasksByCategoryResult = taskController.viewAllTasksByCategory(mockHttpServletRequest);
        assertTrue(actualViewAllTasksByCategoryResult.hasBody());
        assertEquals(HttpStatus.OK, actualViewAllTasksByCategoryResult.getStatusCode());
        assertTrue(actualViewAllTasksByCategoryResult.getHeaders().isEmpty());
        verify(taskService).getAllTasksPerCategory((User) any());
        assertTrue(mockHttpServletRequest.getSession() instanceof MockHttpSession);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasksByCategory(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasksByCategory4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasksByCategory(TaskController.java:71)
        //   In order to prevent viewAllTasksByCategory(HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasksByCategory(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        TaskService taskService = mock(TaskService.class);
        when(taskService.getAllTasksPerCategory((User) any())).thenReturn(new HashMap<>());
        (new TaskController(taskService)).viewAllTasksByCategory(null);
    }

    /**
     * Method under test: {@link TaskController#viewAllTasksByCategory(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testViewAllTasksByCategory5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R008 Failed to instantiate class under test.
        //   Diffblue Cover was unable to construct an instance of TaskController.
        //   Ensure there is a package-visible constructor or factory method that does not
        //   throw for the class under test.
        //   If such a method is already present but Diffblue Cover does not find it, it can
        //   be specified using custom rules for inputs:
        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
        //   This can happen because the factory method takes arguments, throws, returns null
        //   or returns a subtype.
        //   See https://diff.blue/R008 for further troubleshooting of this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.web.TaskController.viewAllTasksByCategory(TaskController.java:71)
        //   In order to prevent viewAllTasksByCategory(HttpServletRequest)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   viewAllTasksByCategory(HttpServletRequest).
        //   See https://diff.blue/R013 to resolve this issue.

        TaskService taskService = mock(TaskService.class);
        when(taskService.getAllTasksPerCategory((User) any())).thenThrow(new NoSuchElementException("USER"));
        TaskController taskController = new TaskController(taskService);
        taskController.viewAllTasksByCategory(new Request(new Connector()));
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

    /**
     * Method under test: {@link TaskController#completeTask(Task)}
     */
    @Test
    void testCompleteTask() throws Exception {
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
        task1.setCompleteBy(LocalDate.ofEpochDay(1L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("Name");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);
        when(taskRepo.save((Task) any())).thenReturn(task1);
        when(taskRepo.findById((Integer) any())).thenReturn(ofResult);

        User user4 = new User();
        user4.setCategories(new ArrayList<>());
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerified(true);
        user4.setId(1);
        user4.setPassword("iloveyou");

        Category category2 = new Category();
        category2.setCategoryId(123);
        category2.setCategoryName("Category Name");
        category2.setTasks(new ArrayList<>());
        category2.setUser(user4);

        User user5 = new User();
        user5.setCategories(new ArrayList<>());
        user5.setEmail("jane.doe@example.org");
        user5.setEmailVerified(true);
        user5.setId(1);
        user5.setPassword("iloveyou");

        Task task2 = new Task();
        task2.setCategory(category2);
        task2.setCompleteBy(null);
        task2.setDescription("The characteristics of someone or something");
        task2.setDone(true);
        task2.setName("Name");
        task2.setReminders(new ArrayList<>());
        task2.setRepeatEvery(1);
        task2.setTaskId(123);
        task2.setUser(user5);
        String content = (new ObjectMapper()).writeValueAsString(task2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/resolve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("123"));
    }

    /**
     * Method under test: {@link TaskController#completeTask(Task)}
     */
    @Test
    void testCompleteTask2() throws Exception {
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
        when(taskRepo.save((Task) any())).thenThrow(new NoSuchElementException("?"));
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/task/resolve")
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

