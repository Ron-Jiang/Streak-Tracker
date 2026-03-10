package com.streaktracker.streak_tracker.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.streaktracker.streak_tracker.entities.*;
import com.streaktracker.streak_tracker.repositories.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HabitServicesImpl implements HabitServices {
    private final HabitRepository habitRepository;
    private final CompletionRepository completionRepository;

    private final String userId = "temp";
        // create a habit
    public Habit createHabit(String name, String description) {
        Habit newHabit = Habit.builder()
                            .userId(userId)
                            .habitName(name)
                            .habitDescription(description)
                            .createdAt(Instant.now())
                            .build();
        return habitRepository.save(newHabit);
    }

    // get Habit
    public List<Habit> habitsUpdate() {
        List<Habit> habits = habitRepository.findByUserId(userId);
        habits.forEach(this::resetHabitIfNeeded);
        return habits;
    }

    // update existing Habit
    public Habit updateHabit(String habitId, String name, String description) {
        Habit theHabit = habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));
        theHabit.setHabitName(name);
        theHabit.setHabitDescription(description);
        return habitRepository.save(theHabit);
    }

    // delete existing habit
    public void deleteHabit(String habitId) {
        habitRepository.deleteById(habitId);
    }

    // complete habit
    public Habit completeHabitToday(String habitId) {
        Habit theHabit = habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));
        
        LocalDate today = LocalDate.now(ZoneId.of("America/Los_Angeles"));
        LocalDate yesterday = today.minusDays(1);
        if (theHabit.getLastCompleted() == null) {
            theHabit.setCurrentStreak(1);
        } else if (theHabit.getLastCompleted().equals(yesterday)) {
            theHabit.setCurrentStreak(theHabit.getCurrentStreak() + 1);
        } else {
            theHabit.setCurrentStreak(1);
        }

        theHabit.setLastCompleted(today);
        if (theHabit.getCurrentStreak() > theHabit.getLongestStreak()) {
            theHabit.setLongestStreak(theHabit.getCurrentStreak());
        }

        habitRepository.save(theHabit);

        Completion completion = Completion.builder()
                                    .habitId(habitId)
                                    .userId(userId)
                                    .completedHour(ZonedDateTime.now(ZoneId.of("America/Los_Angeles")).getHour())
                                    .build();
        completionRepository.save(completion);
        return theHabit;
    }
    // reset habit streak
    public void resetHabitIfNeeded(Habit habit) {
        if (habit.getLastCompleted() == null)
            return;
        LocalDate today = LocalDate.now(ZoneId.of("America/Los_Angeles"));
        LocalDate yesterday = today.minusDays(1);
        if (!habit.getLastCompleted().equals(today) && !habit.getLastCompleted().equals(yesterday)) {
            habit.setCurrentStreak(0);
            habitRepository.save(habit);
        }
    }
}
