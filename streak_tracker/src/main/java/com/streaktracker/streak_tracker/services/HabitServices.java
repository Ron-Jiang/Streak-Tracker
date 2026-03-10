package com.streaktracker.streak_tracker.services;

import java.util.List;
import com.streaktracker.streak_tracker.entities.Habit;

public interface HabitServices {
    // create a habit
    Habit createHabit(String name, String description);
    // get habits
    List<Habit> habitsUpdate();
    // update existing habits
    Habit updateHabit(String habitId, String name, String description);
    // delete existing habit
    void deleteHabit(String habitId);
    // complete habit
    Habit completeHabitToday(String habitId);
    // reset habit streak
    void resetHabitIfNeeded(Habit habit);
}
