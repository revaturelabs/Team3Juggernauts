package com.juggernauts.todoapp.configurations.interceptors.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.juggernauts.todoapp.repos.ReminderRepo;
import org.junit.jupiter.api.Test;

class ReminderServiceTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link ReminderService}
     *   <li>{@link ReminderService#setReminderRepo(ReminderRepo)}
     * </ul>
     */
    @Test
    void testConstructor() {
        ReminderService actualReminderService = new ReminderService();
        actualReminderService.setReminderRepo(mock(ReminderRepo.class));
        assertTrue(actualReminderService.getAllOutdatedReminders().isEmpty());
    }
}

