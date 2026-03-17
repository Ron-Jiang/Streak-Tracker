package com.streaktracker.streak_tracker.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.streaktracker.streak_tracker.entities.Habit;
import com.streaktracker.streak_tracker.services.HabitServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/habits")
@AllArgsConstructor
@CrossOrigin
public class HabitController {
    private final HabitServices habitServices;

    private String getUserId(OAuth2User principal) {
        return principal.getAttribute("id").toString();
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit, @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(habitServices.createHabit(getUserId(principal), habit.getHabitName(), habit.getHabitDescription()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable String id, @AuthenticationPrincipal OAuth2User principal) {
        habitServices.deleteHabit(getUserId(principal), id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Habit> completeHabitToday(@PathVariable String id, @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(habitServices.completeHabitToday(getUserId(principal), id));
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits(@AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(habitServices.getAllHabits(getUserId(principal)));
    }

    @PostMapping("/check")
    public ResponseEntity<List<Habit>> checkAllHabits(@AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(habitServices.habitsUpdate(getUserId(principal)));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Habit>> getLeaderboard() {
        return ResponseEntity.ok(habitServices.getLeaderboard());
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Habit> updateHabit(@PathVariable String id, @RequestBody Habit habit) {
    //     return ResponseEntity.ok(habitServices.updateHabit(id, habit.getHabitName(), habit.getHabitDescription()));
    // }

    // // // // // // debugging endpoints // // // // // //
    // @PostMapping("/manualtest")
    // public ResponseEntity<Habit> manuallySetHabit(@RequestBody Habit habit) {
    //     return ResponseEntity.ok(habitServices.manuallySetHabit(habit.getHabitName(), habit.getHabitDescription(),
    //             habit.getCurrentStreak(), habit.getLongestStreak(), habit.getLastCompleted()));
    // }
}
