package com.example.hw8.game.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    PLAYER("player");

    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
