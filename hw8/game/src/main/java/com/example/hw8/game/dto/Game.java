package com.example.hw8.game.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private String user;
    private String gameId;
    private boolean permissionFlag;
}
