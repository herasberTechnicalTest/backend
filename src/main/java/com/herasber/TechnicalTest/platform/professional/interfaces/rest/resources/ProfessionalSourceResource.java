package com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record ProfessionalSourceResource(
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
        String mapsUrl,
        String whatsappLink
) {
}
