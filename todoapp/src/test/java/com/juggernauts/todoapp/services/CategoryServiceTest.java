package com.juggernauts.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.CategoryRepo;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryService categoryService;

    /**
     * Method under test: {@link CategoryService#addCategory(Category)}
     */
    @Test
    void testAddCategory() throws InvalidParameterException {
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
        when(categoryRepo.save((Category) any())).thenReturn(category);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("Category Name");
        category1.setTasks(new ArrayList<>());
        category1.setUser(user1);
        categoryService.addCategory(category1);
        verify(categoryRepo).save((Category) any());
        assertEquals(123, category1.getCategoryId());
        assertSame(user1, category1.getUser());
        assertTrue(category1.getTasks().isEmpty());
        assertEquals("Category Name", category1.getCategoryName());
    }

    /**
     * Method under test: {@link CategoryService#addCategory(Category)}
     */
    @Test
    void testAddCategory2() throws InvalidParameterException {
        when(categoryRepo.save((Category) any())).thenThrow(new InvalidParameterException("foo"));

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
        assertThrows(InvalidParameterException.class, () -> categoryService.addCategory(category));
        verify(categoryRepo).save((Category) any());
    }

    /**
     * Method under test: {@link CategoryService#getAllCategoriesForUser(User)}
     */
    @Test
    void testGetAllCategoriesForUser() {
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        assertTrue(categoryService.getAllCategoriesForUser(user).isEmpty());
        verify(categoryRepo).findAll();
    }

    /**
     * Method under test: {@link CategoryService#getAllCategoriesForUser(User)}
     */
    @Test
    void testGetAllCategoriesForUser2() {
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

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");
        assertEquals(1, categoryService.getAllCategoriesForUser(user1).size());
        verify(categoryRepo).findAll();
    }

    /**
     * Method under test: {@link CategoryService#getAllCategoriesForUser(User)}
     */
    @Test
    void testGetAllCategoriesForUser3() {
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

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("Category Name");
        category1.setTasks(new ArrayList<>());
        category1.setUser(user1);

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");
        assertEquals(2, categoryService.getAllCategoriesForUser(user2).size());
        verify(categoryRepo).findAll();
    }

    /**
     * Method under test: {@link CategoryService#getAllCategoriesForUser(User)}
     */
    @Test
    void testGetAllCategoriesForUser4() {
        when(categoryRepo.findAll()).thenThrow(new InvalidParameterException("foo"));

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        assertThrows(InvalidParameterException.class, () -> categoryService.getAllCategoriesForUser(user));
        verify(categoryRepo).findAll();
    }

    /**
     * Method under test: {@link CategoryService#getAllCategoriesForUser(User)}
     */
    @Test
    void testGetAllCategoriesForUser5() {
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

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(123);
        user1.setPassword("iloveyou");
        assertTrue(categoryService.getAllCategoriesForUser(user1).isEmpty());
        verify(categoryRepo).findAll();
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link CategoryService}
     *   <li>{@link CategoryService#setCategoryRepo(CategoryRepo)}
     * </ul>
     */

    @Test
    void testConstructor() {
        CategoryService actualCategoryService = new CategoryService();
        actualCategoryService.setCategoryRepo(mock(CategoryRepo.class));
        assertNull(actualCategoryService.currentUser);
    }
}

