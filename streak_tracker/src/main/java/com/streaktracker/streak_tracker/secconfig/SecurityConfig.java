package com.streaktracker.streak_tracker.secconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import com.streaktracker.streak_tracker.secconfig.OAuth2SuccessHandler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> 
            csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers("/login/**", "/error", "/oauth2/**").permitAll().anyRequest().authenticated())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .oauth2Login(o -> o.defaultSuccessUrl("http://localhost:5173", true))
            .logout(l -> l.logoutSuccessUrl("http://localhost:5173").permitAll());
            // .oauth2Login(oauth2 -> oauth2.successHandler(oAuth2SuccessHandler));
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlSource = new UrlBasedCorsConfigurationSource();
        urlSource.registerCorsConfiguration("/**", corsConfig);
        return urlSource;
    }
}
