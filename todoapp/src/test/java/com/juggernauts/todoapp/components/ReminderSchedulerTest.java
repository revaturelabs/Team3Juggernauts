package com.juggernauts.todoapp.components;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.MailService;
import com.juggernauts.todoapp.services.ReminderService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReminderSchedulerTest {
    @Mock
    private Reminder mockReminder;
    @Mock
    private Task mockTask;

    @MockBean
    private ReminderService reminderService;
    @MockBean
    private MailService mailService;
    @Autowired
    private ReminderScheduler reminderScheduler;

    private List<Reminder> outdatedReminders;

    @Before
    public void init() {
        mockReminder = mock(Reminder.class);
        mockTask = mock(Task.class);
        when(mockTask.getUser()).thenReturn(mock(User.class));
        when(mockReminder.getTask()).thenReturn(mockTask);

        outdatedReminders = new ArrayList<>();
        outdatedReminders.add(mockReminder);

        when(reminderService.getAllOutdatedReminders()).thenReturn(outdatedReminders);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                outdatedReminders.clear();
                return null;
            }
        }).when(reminderService).deleteReminders(anyList());
    }

    /**
     * Method under test: {@link ReminderScheduler#scheduleReminderNotifier()}
     */
    @org.junit.jupiter.api.Test
    void testScheduleReminderNotifier() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ReminderScheduler.mailService
        //     ReminderScheduler.reminderService

        reminderScheduler.scheduleReminderNotifier();
    }

    @Test
    public void givenSleepBy10Seconds_whenGetReminders_thenIsEmpty() throws InterruptedException {
        reminderScheduler.scheduleReminderNotifier();
        assertTrue(outdatedReminders.isEmpty());
    }
}
