package com.herasber.platform.professional.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record CreateProfessionalSourceResource(
        String fullName,
        String phone,
        String servicesDescription,
        String photoUrl,
        List<String> gallery,
        BigDecimal rate,
        String currency,
        String cityName,
        String districtName,
        String mapsUrl
) {
    public CreateProfessionalSourceResource {
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("fullName cannot be null or empty");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be null or empty");
        if (servicesDescription == null || servicesDescription.isBlank())
            throw new IllegalArgumentException("servicesDescription cannot be null or empty");
        if (cityName == null || cityName.isBlank())
            throw new IllegalArgumentException("cityName cannot be null or empty");
        if (districtName == null || districtName.isBlank())
            throw new IllegalArgumentException("districtName cannot be null or empty");
    }
}