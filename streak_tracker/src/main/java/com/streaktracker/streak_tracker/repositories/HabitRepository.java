package com.streaktracker.streak_tracker.repositories;

import java.util.List;

import com.streaktracker.streak_tracker.entities.Habit;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HabitRepository extends MongoRepository<Habit, String> {
    List<Habit> findByUserId(String userId); 
}
