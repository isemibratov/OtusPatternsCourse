package com.example.hw8.game.service;

import static java.util.Collections.emptyList;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    private final HashMap<String, List<String>> games;

    public GameService(){
        this.games = new HashMap<>();
    }

    public String createGame(List<String> users) {
        var gameId = UUID.randomUUID().toString();
        games.put(gameId, users);

        return gameId;
    }

    public boolean checkPermissionForGame(String gameId, String user) {
        return games
                .getOrDefault(gameId, emptyList())
                .contains(user);
    }
}
