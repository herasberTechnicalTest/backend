package com.herasber.TechnicalTest.platform.professional.domain.services;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProfessionalSourceQueryService {
    List<ProfessionalSource> handleGetAllByCity(String cityName);
    List<ProfessionalSource> handleGetAllByDistrict(String districtName);
    List<ProfessionalSource> handleGetAllByRate(BigDecimal rate);
    Optional<ProfessionalSource> handleGetById(Long id);
    List<ProfessionalSource> handleGetAll();
}
