package com.streaktracker.streak_tracker.services;

import java.util.List;
import com.streaktracker.streak_tracker.entities.Habit;

public interface HabitServices {
    // create a habit
    Habit createHabit(String userId, String name, String description);

    // get habits
    List<Habit> habitsUpdate(String userId);

    // delete existing habit
    void deleteHabit(String userId, String habitId);

    // complete habit
    Habit completeHabitToday(String userId, String habitId);

    // reset habit streak
    void resetHabitIfNeeded(Habit habit);

    // get all habits
    List<Habit> getAllHabits(String userId);

    // // update existing habits
    // Habit updateHabit(String habitId, String name, String description);

    // // // // // // debugging services // // // // // //
    // Habit manuallySetHabit(String name, String description, Integer curentStreak, Integer longestStreak,
    //         LocalDate lastCompleted);
}
