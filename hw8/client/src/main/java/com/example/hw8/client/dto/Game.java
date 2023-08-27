package com.example.hw8.client.dto;

import lombok.Data;

@Data
public class Game {
    private String user;
    private String gameId;
    private boolean permissionFlag;
}
