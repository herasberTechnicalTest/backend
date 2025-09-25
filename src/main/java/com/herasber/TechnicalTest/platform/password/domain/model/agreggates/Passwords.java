package com.herasber.TechnicalTest.platform.password.domain.model.agreggates;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class Passwords {
    private static final BCryptPasswordEncoder ENC = new BCryptPasswordEncoder();

    private Passwords() { }

    public static String hash(String raw) {
        if (raw == null || raw.isBlank()) throw new IllegalArgumentException("password cannot be null/blank");
        return ENC.encode(raw);
    }

    public static boolean matches(String raw, String hash) {
        if (raw == null || hash == null) return false;
        return ENC.matches(raw, hash);
    }
}

