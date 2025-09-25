package com.herasber.TechnicalTest.platform.professional.infrastructure.persistance.jpa;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalSourceRepository extends JpaRepository<ProfessionalSource, Long> {
    List<ProfessionalSource> findAll();
    List<ProfessionalSource> findAllByLocation_CityName(String cityName);
    List<ProfessionalSource> findAllByRate(BigDecimal cityName);
    List<ProfessionalSource> findAllByLocation_DistrictName(String districtName);
    Optional<ProfessionalSource> findByEmail(String email);
    boolean existsByEmailNorm(String emailNorm);
    Optional<ProfessionalSource> findByEmailNorm(String emailNorm);
}