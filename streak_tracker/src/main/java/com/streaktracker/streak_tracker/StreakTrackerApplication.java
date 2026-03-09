package com.streaktracker.streak_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreakTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreakTrackerApplication.class, args);
	}

}

/*
brew services start mongodb-community@8.2
mongosh
brew services stop mongodb-community@8.2
*/
