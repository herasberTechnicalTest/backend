package com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record CreateProfessionalSourceResource(
        String fullName,
        String email,
        String password,
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
) {
    public CreateProfessionalSourceResource {
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("fullName cannot be null or empty");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("phone cannot be null or empty");
        if (servicesDescription == null || servicesDescription.isBlank())
            throw new IllegalArgumentException("servicesDescription cannot be null or empty");
        if (countryName == null || countryName.isBlank())
            throw new IllegalArgumentException("countryName cannot be null or empty");
        if (cityName == null || cityName.isBlank())
            throw new IllegalArgumentException("cityName cannot be null or empty");
        if (districtName == null || districtName.isBlank())
            throw new IllegalArgumentException("districtName cannot be null or empty");
        if (email==null||email.isBlank()) throw new IllegalArgumentException("email required");
        if (password==null||password.isBlank()) throw new IllegalArgumentException("password required");

        fullName = fullName.trim();
        phone = phone.trim();
        servicesDescription = servicesDescription.trim();
        countryName = countryName.trim();
        cityName = cityName.trim();
        districtName = districtName.trim();
        password = password.trim();
        email = email.trim();


        photoUrl = (photoUrl == null || photoUrl.isBlank()) ? null : photoUrl.trim();
        currency = (currency == null || currency.isBlank()) ? null : currency.trim();
        mapsUrl = (mapsUrl == null || mapsUrl.isBlank()) ? null : mapsUrl.trim();

        gallery = (gallery == null) ? List.of() : List.copyOf(gallery);

        if (rate != null && rate.signum() < 0) {
            throw new IllegalArgumentException("rate cannot be negative");
        }
    }
}