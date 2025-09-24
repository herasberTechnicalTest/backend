package com.herasber.TechnicalTest.platform.professional.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProfessionalSourceCommand(
        Long id,
        String fullName,
        String phone,
        String servicesDescription,
        String photoUrl,
        List<String> gallery,
        BigDecimal rate,
        String currency,
        String countryName,
        String cityName,
        String districtName,
        String mapsUrl
) { }