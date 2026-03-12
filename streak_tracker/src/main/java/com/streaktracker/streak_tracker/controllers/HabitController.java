package com.streaktracker.streak_tracker.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streaktracker.streak_tracker.entities.Habit;
import com.streaktracker.streak_tracker.services.HabitServices;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/habits")
@AllArgsConstructor
@CrossOrigin
public class HabitController {
    private final HabitServices habitServices;

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit) {
        return ResponseEntity.ok(habitServices.createHabit(habit.getHabitName(), habit.getHabitDescription()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable String id, @RequestBody Habit habit) {
        return ResponseEntity.ok(habitServices.updateHabit(id, habit.getHabitName(), habit.getHabitDescription()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable String id) {
        habitServices.deleteHabit(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Habit> completeHabitToday(@PathVariable String id) {
        return ResponseEntity.ok(habitServices.completeHabitToday(id));
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        return ResponseEntity.ok(habitServices.getAllHabits());
    }

    @PostMapping("/check")
    public ResponseEntity<List<Habit>> checkAllHabits() {
        return ResponseEntity.ok(habitServices.habitsUpdate());
    }

    // // // // // // debugging endpoints // // // // // //
    @PostMapping("/manualtest")
    public ResponseEntity<Habit> manuallySetHabit(@RequestBody Habit habit) {
        return ResponseEntity.ok(habitServices.manuallySetHabit(habit.getHabitName(), habit.getHabitDescription(),
                habit.getCurrentStreak(), habit.getLongestStreak(), habit.getLastCompleted()));
    }
}
