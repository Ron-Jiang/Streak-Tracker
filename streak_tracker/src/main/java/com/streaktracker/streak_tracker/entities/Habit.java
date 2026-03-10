package com.streaktracker.streak_tracker.entities;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "habits")

public class Habit {
    @Id
    private String id;

    @Indexed
    private String userId;

    private String habitName;
    private String habitDescription;

    @Builder.Default
    private Integer currentStreak = 0;

    @Builder.Default
    private Integer longestStreak = 0;

    private LocalDate lastCompleted;

    private Instant createdAt;
}
