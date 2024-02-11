package com.quizapp.quiz.core.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtProperties {
    private String secretKey;

    private long expiration;

    private long refreshTokenExpiration;
}
