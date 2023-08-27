package com.example.hw8.client.controller;

import com.example.hw8.client.config.Paths;
import com.example.hw8.client.dto.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties(Paths.class)
@Slf4j
public class ClientController {
    private final Paths paths;
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();
    {
        restTemplate.getInterceptors().add(new AuthorizationInterceptor());
    }

    @GetMapping("/queryToken")
    public String queryToken(String user, String password) {
        var url = UriComponentsBuilder.fromHttpUrl(paths.getAuth() + "/authenticate")
                .queryParam("user", user)
                .queryParam("password", password)
                .toUriString();

        log.info("Query: {}", url);

        token = new RestTemplate().getForObject(url, String.class);

        log.info("Result: {}", token);

        return token;
    }

    @PostMapping("/game")
    public Game createGame(
            List<String> participants
    ) {
        var url = UriComponentsBuilder.fromHttpUrl(paths.getGame() + "/game")
                .toUriString();

        log.info("Query: {}", url);

        var game = new RestTemplate().postForObject(url, participants, Game.class);

        log.info("Result: gameId={}, permissionFlag={}", game.getGameId(), game.isPermissionFlag());

        return game;
    }

    @GetMapping("/game")
    public Game getGame(
            String gameId
    ) {
        var url = UriComponentsBuilder.fromHttpUrl(paths.getGame() + "/game")
                .queryParam("gameId", gameId)
                .toUriString();

        log.info("Query: {}", url);

        var game = new RestTemplate().getForObject(url, Game.class);

        log.info("Result: gameId={}, permissionFlag={}", game.getGameId(), game.isPermissionFlag());

        return game;
    }

    private class AuthorizationInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().add("Authorization", "Bearer " + token);
            return execution.execute(request, body);
        }
    }
}
