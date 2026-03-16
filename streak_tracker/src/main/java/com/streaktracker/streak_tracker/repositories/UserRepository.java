package com.streaktracker.streak_tracker.repositories;

import java.util.Optional;

import com.streaktracker.streak_tracker.entities.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
