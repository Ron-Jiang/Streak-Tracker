package com.streaktracker.streak_tracker.repositories;

import org.springframework.stereotype.Repository;

import com.streaktracker.streak_tracker.entities.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
