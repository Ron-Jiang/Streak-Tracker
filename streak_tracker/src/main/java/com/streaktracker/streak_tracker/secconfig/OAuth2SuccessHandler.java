// package com.streaktracker.streak_tracker.secconfig;

// import java.io.IOError;
// import java.io.IOException;
// import java.net.Authenticator;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.stereotype.Component;

// import com.streaktracker.streak_tracker.services.JwtServices;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @Component
// public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
//     private final JwtServices jwtServices;

//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//         OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//         String userid = oAuth2User.getAttribute("id").toString();
//         System.out.println("SUCCESS HANDER USER: " + userid + "=================================");
//         String token = jwtServices.generateJwtToken(userid);
//         System.out.println("GENERATED TOKEN: " + token + " =======================================================");
//         System.out.println("ABOUT TO DIRECT TO: " + "http://localhost:5173?token="+token);
//         response.sendRedirect("http://localhost:5173?token="+token);
//     }
// }
