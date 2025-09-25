package com.herasber.TechnicalTest.platform.Auth.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleTokenService {
    private final Map<String, Long> tokens = new ConcurrentHashMap<>();

    public String issue(Long professionalId) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, professionalId);
        return token;
    }

    public Optional<Long> resolve(String token) {
        return Optional.ofNullable(tokens.get(token));
    }

    public void revoke(String token) {
        tokens.remove(token);
    }
}
