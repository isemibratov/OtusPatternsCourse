package com.example.hw8.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("msa.path")
public class Paths {
    private String auth;
    private String game;
}
