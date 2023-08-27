package com.example.hw8.game.config;

import com.example.hw8.game.auth.AuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private @Value("${msa.auth.public}") String publicKey;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v*/api-docs/**", "/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(new AuthFilter(publicKey), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(cfg -> cfg
                        .anyRequest().authenticated()
                );
    }
}
