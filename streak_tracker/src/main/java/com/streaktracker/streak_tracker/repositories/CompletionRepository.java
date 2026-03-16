package com.streaktracker.streak_tracker.repositories;

import java.util.List;

import com.streaktracker.streak_tracker.entities.Completion;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompletionRepository extends MongoRepository<Completion, String> {
    // ordered by completed time
    List<Completion> findByHabitId(String habitId);
}

