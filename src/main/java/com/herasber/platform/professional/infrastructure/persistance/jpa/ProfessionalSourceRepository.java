package com.herasber.platform.professional.infrastructure.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalSourceRepository extends JpaRepository<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource, Long> {
    List<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource> findAllByLocation_CityName(String cityName);

    List<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource> findAllByLocation_DistrictName(String districtName);
}