package com.herasber.platform.professional.domain.services;

import java.util.List;
import java.util.Optional;

public interface ProfessionalSourceQueryService {
    List<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource> handleGetAllByCity(String cityName);
    List<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource> handleGetAllByDistrict(String districtName);
    Optional<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource> handleGetById(Long id);
}
