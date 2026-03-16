package com.streaktracker.streak_tracker.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.streaktracker.streak_tracker.entities.*;
import com.streaktracker.streak_tracker.repositories.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HabitServicesImpl implements HabitServices {
    private final HabitRepository habitRepository;
    private final CompletionRepository completionRepository;

    // create a habit
    public Habit createHabit(String userId, String name, String description) {
        Habit newHabit = Habit.builder()
                .userId(userId)
                .habitName(name)
                .habitDescription(description)
                .createdAt(Instant.now())
                .build();
        return habitRepository.save(newHabit);
    }

    // get Habit
    public List<Habit> habitsUpdate(String userId) {
        List<Habit> habits = habitRepository.findByUserId(userId);
        habits.forEach(this::resetHabitIfNeeded);
        return habits;
    }

    // delete existing habit
    public void deleteHabit(String userId, String habitId) {
        Habit habitToBeDeleted = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        if (!habitToBeDeleted.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized for user");
        }
        habitRepository.deleteById(habitId);
    }

    // complete habit
    public Habit completeHabitToday(String userId, String habitId) {
        Habit theHabit = habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));
        if (!theHabit.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized for user");
        }

        LocalDate today = LocalDate.now(); // system time
        LocalDate yesterday = today.minusDays(1);
        if (theHabit.getLastCompleted() == null) {
            theHabit.setCurrentStreak(1);
        } else if (theHabit.getLastCompleted().equals(yesterday)) {
            theHabit.setCurrentStreak(theHabit.getCurrentStreak() + 1);
        } else if (theHabit.getLastCompleted().equals(today)) {
            return theHabit;
        } else {
            theHabit.setCurrentStreak(1);
        }

        theHabit.setLastCompleted(today);
        if (theHabit.getCurrentStreak() > theHabit.getLongestStreak()) {
            theHabit.setLongestStreak(theHabit.getCurrentStreak());
        }

        habitRepository.save(theHabit);

        Completion completion = Completion.builder()
                .userId(userId)
                .habitId(habitId)
                .completedHour(ZonedDateTime.now().getHour()) // system time?
                .build();
        completionRepository.save(completion);
        return theHabit;
    }

    // reset habit streak
    public void resetHabitIfNeeded(Habit habit) {
        if (habit.getLastCompleted() == null)
            return;
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        if (!habit.getLastCompleted().equals(today) && !habit.getLastCompleted().equals(yesterday)) {
            habit.setCurrentStreak(0);
            habitRepository.save(habit);
        }
    }

    public List<Habit> getAllHabits(String userId) {
        return habitRepository.findByUserId(userId);
    }

    // // update existing Habit
    // public Habit updateHabit(String habitId, String name, String description) {
    // Habit theHabit = habitRepository.findById(habitId).orElseThrow(() -> new
    // RuntimeException("Habit not found"));
    // if (name != null && !name.isBlank())
    // theHabit.setHabitName(name);
    // if (description != null && !description.isBlank())
    // theHabit.setHabitDescription(description);
    // return habitRepository.save(theHabit);
    // }

    // // // // // // debugging services // // // // // //
    // public Habit manuallySetHabit(String name, String description, Integer
    // curentStreak, Integer longestStreak,
    // LocalDate lastCompleted) {
    // Habit newHabit = Habit.builder()
    // .userId(userId)
    // .habitName(name)
    // .habitDescription(description)
    // .currentStreak(curentStreak)
    // .longestStreak(longestStreak)
    // .lastCompleted(lastCompleted)
    // .createdAt(Instant.now())
    // .build();
    // return habitRepository.save(newHabit);
    // }
}
