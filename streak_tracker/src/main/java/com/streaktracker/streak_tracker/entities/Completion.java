package com.streaktracker.streak_tracker.entities;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
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
@Document(collection = "completions")
@CompoundIndex(def = "{'habitId': 1, 'completedAt': -1}")
public class Completion {
    @Id
    private String id;

    @Indexed
    private String habitId;

    private String userId;
    private Instant createdAt;

    private int completedHour;
}
