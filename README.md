# Streak-Tracker
A full-stack habit tracking web application that supports the user with maintaining their habits by utilizing a streak system, helping the user visualize their progress, and share a sense of pride in maintaining a high streak.

## Tech Stack
**Backend:** Java, Spring Boot, OAuth 2.0, Spring Security, Maven
<br>**Frontend:** JavaScript, React, Axios, HTML, Tailwind CSS
<br>**Database:** MongoDB
<br>**Testing:** JUnit, Mockito, JaCoCo

## Features:
- **GitHub OAuth2 Login:** Secure authentication using a GitHub account
- **Creating Habits:** Create new habits by giving it a name and description
- **Streak Tracking:** Mark a habit as complete each calender day to increment streak
- **Automatic Streak Reset:** If a habit is not completed within the calender day, the streak will reset to 0
- **Longest Streak Tracking:** Records the highest streak achieved for the user
- **Per-User Data:** Each user sees and manages their own habits

## To Get Started:
- Java 24
- Node.js
- MongoDB running locally on port 27017
- GitHub OAuth credentials

## Backend Setup:
1. Clone this repository
2. Create `src/main/resources/application.properties`
```properties
spring.application.name=streak_tracker
spring.mongodb.uri=mongodb://localhost:27017/streaktracker
server.error.include-message=always
server.error.include-binding-errors=always
spring.security.oauth2.client.registration.github.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=YOUR_CLIENT_SECRET
```
3. Run the backend:
```terminal
mvn spring-boot:run
```

## Frontend Setup:
1. Go to frontend directory
2. Install dependencies:
```terminal
npm install
```
3. Start the server:
```terminal
npm run dev
```
4. Open `http://localhost:5173` in your browser

## Testing:
Unit tests cover the habit service layer
```terminal
mvn test
```
JaCoCo coverage report is generated at `target/site/jacoco/index.html`, currently at 94% habit service layer coverage

By @Ron-Jiang
