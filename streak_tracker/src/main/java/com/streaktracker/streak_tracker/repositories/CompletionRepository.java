package com.streaktracker.streak_tracker.repositories;

import org.springframework.stereotype.Repository;

import com.streaktracker.streak_tracker.entities.Completion;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface CompletionRepository extends MongoRepository<Completion, String> {
    // ordered by completed time
    List<Completion> findByHabitId(String habitId);
}

