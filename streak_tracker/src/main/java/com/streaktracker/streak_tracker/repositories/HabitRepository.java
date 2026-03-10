package com.streaktracker.streak_tracker.repositories;

import org.springframework.stereotype.Repository;

import com.streaktracker.streak_tracker.entities.Habit;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface HabitRepository extends MongoRepository<Habit, String> {
    List<Habit> findByUserId(String userId); 
}
