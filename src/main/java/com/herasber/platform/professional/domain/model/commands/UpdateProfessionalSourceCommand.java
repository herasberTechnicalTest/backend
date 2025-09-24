package com.herasber.platform.professional.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record UpdateProfessionalSourceCommand(
        @NotBlank String fullName,
        @NotBlank String servicesDescription,
        @NotBlank String phone,
        String photoUrl,
        // location
        @NotBlank String cityName,
        @NotBlank String districtName,
        String mapsUrl
) { }