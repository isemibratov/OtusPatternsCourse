package com.example.hw8.game.controller;

import com.example.hw8.game.dto.Game;
import com.example.hw8.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/game")
    @PreAuthorize("permittedByRole('$Role.PLAYER')")
    public Game postGame(List<String> users, Authentication authentication) {
        var userName = authentication.getName();
        var gameId = gameService.createGame(users);
        return Game.builder()
                .user(userName)
                .gameId(gameId)
                .permissionFlag(gameService.checkPermissionForGame(gameId, userName))
                .build();
    }

    @GetMapping("/game")
    @PreAuthorize("permittedByRole('$Role.PLAYER')")
    public Game getGame(String gameId, Authentication authentication) {
        var userName = authentication.getName();
        return Game.builder()
                .user(userName)
                .gameId(gameId)
                .permissionFlag(gameService.checkPermissionForGame(gameId, userName))
                .build();
    }
}
