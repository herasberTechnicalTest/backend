package com.herasber.TechnicalTest.platform.professional.domain.model.commands;

import java.math.BigDecimal;
import java.util.List;

public record CreateProfessionalSourceCommand(

        String fullName,
        String email,
        String password,
        String phone,
        String servicesDescription,
        String photoUrl,
        java.util.List<String> gallery,
        java.math.BigDecimal rate,
        String currency,
        String countryName,
        String cityName,
        String districtName
) {
public CreateProfessionalSourceCommand {
    if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("fullName cannot be null or empty");
    if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be null or empty");
    if (servicesDescription == null || servicesDescription.isBlank()) throw new IllegalArgumentException("servicesDescription cannot be null or empty");
    if (cityName == null || cityName.isBlank()) throw new IllegalArgumentException("cityName cannot be null or empty");
    if (districtName == null || districtName.isBlank()) throw new IllegalArgumentException("districtName cannot be null or empty");
    if (email==null||email.isBlank()) throw new IllegalArgumentException("email required");
    if (password==null||password.isBlank()) throw new IllegalArgumentException("password required");

    }
}
