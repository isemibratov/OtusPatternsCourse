package com.example.hw8.auth.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String name;
    private String password;
    private boolean player = false;
}
