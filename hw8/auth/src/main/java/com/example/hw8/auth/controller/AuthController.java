package com.example.hw8.auth.controller;

import com.example.hw8.auth.service.JwtCreator;
import com.example.hw8.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private static final List<User> users = List.of(
            new User().setName("nonPlayer").setPassword("123"),
            new User().setName("player").setPassword("123").setPlayer(true));

    private final JwtCreator jwtCreator;

    @GetMapping("/authenticate")
    public String authenticate(String user, String password) {
        return users.stream()
                .filter(e -> e.getName().equals(user) && e.getPassword().equals(password))
                .findFirst()
                .map(jwtCreator::createJwt)
                .orElseThrow(AuthError::new);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static class AuthError extends RuntimeException {}
}
