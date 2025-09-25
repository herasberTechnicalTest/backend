package com.herasber.TechnicalTest.platform.Auth.domain.commands;

public record LoginResponse(String token, Long professionalId, String fullName, String email) {
}
