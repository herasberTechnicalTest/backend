package com.herasber.platform.professional.interfaces.rest.resources;

public record ProfessionalSourceResource(
        Long id, String fullName, String phone, String servicesDescription,
        String photoUrl, String cityName, String districtName,
        String mapsUrl, String whatsappLink
) {
}
