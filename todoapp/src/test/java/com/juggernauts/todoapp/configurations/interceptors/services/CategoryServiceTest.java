package com.juggernauts.todoapp.configurations.interceptors.services;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import com.juggernauts.todoapp.repos.CategoryRepo;
import com.juggernauts.todoapp.services.CategoryService;
import org.junit.jupiter.api.Test;

class CategoryServiceTest {
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

