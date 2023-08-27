package com.example.hw8.game.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class AuthFilter extends OncePerRequestFilter {
    private static final String
            AUTH = "Authorization",
            BEARER = "Bearer ";

    private final JwtParser jwtParser;

    @SneakyThrows
    public AuthFilter(String publicKey) {
        byte[] keyBytes = Base64Utils.decodeFromString(
                publicKey
                        .replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "")
                        .replaceAll("\\s+","")
        );
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(spec);
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        var header = request.getHeader(AUTH);
        if (header == null)
            return true;

        return !header.startsWith(BEARER);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader(AUTH).substring(BEARER.length());

        Claims claims;
        try {
            claims = jwtParser.parseClaimsJws(token).getBody();
        }
        catch (Exception e) {
            setErrorResponse(HttpStatus.FORBIDDEN, response, e);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(new ClaimsAuthentication(claims));

        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    private void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex){
        response.setStatus(status.value());
        response.setContentType("application/json");
        log.error("Auth error", ex);
        response.getWriter().format("{\"error\": \"%s\"}", ex.getMessage());
    }
}
