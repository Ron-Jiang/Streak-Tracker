package com.streaktracker.streak_tracker.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principle) {
        // Collections.singletonMap("login", principle.getAttribute("login"));
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("id", principle.getAttribute("id"));
        userInformation.put("login", principle.getAttribute("login"));
        userInformation.put("name", principle.getAttribute("name"));
        userInformation.put("avatar_url", principle.getAttribute("avatar_url"));
        return userInformation;
    }
}
