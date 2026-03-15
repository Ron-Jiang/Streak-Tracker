package com.streaktracker.streak_tracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.streaktracker.streak_tracker.entities.Habit;
import com.streaktracker.streak_tracker.repositories.CompletionRepository;
import com.streaktracker.streak_tracker.repositories.HabitRepository;
import com.streaktracker.streak_tracker.services.HabitServicesImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HabitServicesTests {
    @Mock
    private HabitRepository habitRepository;
    @Mock
    private CompletionRepository completionRepository;
    @InjectMocks
    private HabitServicesImpl habitServices;

    // ======================================= habit creation test =======================================//
    @Test
    public void createHabitTest() {
        Habit habit = Habit.builder()
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Mockito.when(habitRepository.save(any(Habit.class))).thenReturn(habit);
        habitServices.createHabit("User", "Test", "Test description");
        verify(habitRepository, times(1)).save(any(Habit.class));
    }

    // ======================================= streak reset test =======================================//
    @Test
    public void streakResetsAfterACalenderDayofNonCompletionTest() {
        LocalDate today = LocalDate.now();
        LocalDate dayBeforeYesterday = today.minusDays(2);
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(dayBeforeYesterday)
                .build();
        List<Habit> habits = new ArrayList<>();
        habits.add(habit);
        Mockito.when(habitRepository.findByUserId("User")).thenReturn(habits);

        List<Habit> result = habitServices.habitsUpdate("User");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getCurrentStreak());
    }

    @Test
    public void longestStreakDoesNotResetAlongWithCurrentStreakTest() {
        LocalDate today = LocalDate.now();
        LocalDate dayBeforeYesterday = today.minusDays(2);
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(dayBeforeYesterday)
                .build();
        List<Habit> habits = new ArrayList<>();
        habits.add(habit);
        Mockito.when(habitRepository.findByUserId("User")).thenReturn(habits);

        List<Habit> result = habitServices.habitsUpdate("User");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getCurrentStreak());
        assertEquals(10, result.get(0).getLongestStreak());
    }

    @Test
    public void streakCompletedTodayDoesNotResetTest() {
        LocalDate today = LocalDate.now();
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(today)
                .build();
        habitServices.resetHabitIfNeeded(habit);
        assertEquals(10, habit.getCurrentStreak());
        assertEquals(10, habit.getLongestStreak());
    }

    @Test
    public void nullLastCompletionShouldNotHaveProblemsTest() {
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        habitServices.resetHabitIfNeeded(habit);
        assertEquals(0, habit.getCurrentStreak());
        assertEquals(0, habit.getLongestStreak());
    }

    // ======================================= delete habit test =======================================//
    @Test
    public void deleteHabitWithValidUserIdTest() {
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        habitServices.deleteHabit("User", "1");
        verify(habitRepository, times(1)).deleteById("1");
    }

    @Test
    public void deleteHabitWithInvalidUserIdTest() {
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        assertThrows(RuntimeException.class, () -> habitServices.deleteHabit("User2", "1"));
        verify(habitRepository, times(0)).deleteById("1");
    }

    // ======================================= complete habit test =======================================//
    // should increment the streak from 0 to 1
    @Test
    public void incrementHabitFromZeroTestWithValidUser() {
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        Habit result = habitServices.completeHabitToday("User", "1");
        assertEquals(1, result.getCurrentStreak());
    }

    // should throw RuntimeException due to unmatching userId
    @Test
    public void incrementHabitFromZeroTestWithInvalidUser() {
        Habit habit = Habit.builder()
                .id("1")
                .userId("User1")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        assertThrows(RuntimeException.class, () -> habitServices.completeHabitToday("User2", "1"));
    }

    // should increment the streak from 10 to 11
    @Test
    public void incrementHabitFromTenTestWithValidUser() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(yesterday)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        Habit result = habitServices.completeHabitToday("User", "1");
        assertEquals(11, result.getCurrentStreak());
    }

    @Test
    public void incrementExistingHabitStreakSameDayTest() {
        LocalDate today = LocalDate.now();
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(today)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        Habit result = habitServices.completeHabitToday("User", "1");
        assertEquals(10, result.getCurrentStreak());
    }

    @Test
    public void streakIncrementsToOneAfterReset() {
        LocalDate today = LocalDate.now();
        LocalDate dayBeforeYesterday = today.minusDays(2);
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(dayBeforeYesterday)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        Habit result = habitServices.completeHabitToday("User", "1");
        assertEquals(1, result.getCurrentStreak());
    }

    @Test
    public void longestStreakIncrementsWithStreakTest() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Habit habit = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test")
                .habitDescription("Test description")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(yesterday)
                .build();
        Mockito.when(habitRepository.findById("1")).thenReturn(Optional.of(habit));

        Habit result = habitServices.completeHabitToday("User", "1");
        assertEquals(11, result.getCurrentStreak());
        assertEquals(11, result.getLongestStreak());
    }

    // ======================================= get all habits test =======================================//
    @Test
    public void getAllHabitsFromValidUser1Test() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Habit habit1 = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test1")
                .habitDescription("Test description1")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(yesterday)
                .build();
        Habit habit2 = Habit.builder()
                .id("2")
                .userId("User")
                .habitName("Test2")
                .habitDescription("Test description2")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Habit habit3 = Habit.builder()
                .id("3")
                .userId("OtherUser")
                .habitName("Test3")
                .habitDescription("Test description3")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        List<Habit> allValidHabits = new ArrayList<>();
        allValidHabits.add(habit1);
        allValidHabits.add(habit2);
        Mockito.when(habitRepository.findByUserId("User")).thenReturn(allValidHabits);

        List<Habit> result = habitServices.getAllHabits("User");
        
        assertEquals(allValidHabits, result);
    }

    @Test
    public void getAllHabitsFromValidUser2Test() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        Habit habit1 = Habit.builder()
                .id("1")
                .userId("User")
                .habitName("Test1")
                .habitDescription("Test description1")
                .currentStreak(10)
                .longestStreak(10)
                .lastCompleted(yesterday)
                .build();
        Habit habit2 = Habit.builder()
                .id("2")
                .userId("User")
                .habitName("Test2")
                .habitDescription("Test description2")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        Habit habit3 = Habit.builder()
                .id("3")
                .userId("OtherUser")
                .habitName("Test3")
                .habitDescription("Test description3")
                .currentStreak(0)
                .longestStreak(0)
                .lastCompleted(null)
                .build();
        List<Habit> allValidHabits = new ArrayList<>();
        allValidHabits.add(habit3);
        Mockito.when(habitRepository.findByUserId("OtherUser")).thenReturn(allValidHabits);

        List<Habit> result = habitServices.getAllHabits("OtherUser");
        
        assertEquals(allValidHabits, result);
    }

    @Test
    public void getAllHabitsFromInalidUserTest() {
        List<Habit> allValidHabits = new ArrayList<>();
        Mockito.when(habitRepository.findByUserId("User3")).thenReturn(allValidHabits);

        List<Habit> result = habitServices.getAllHabits("User3");
        
        assertEquals(allValidHabits, result);
    }

}
