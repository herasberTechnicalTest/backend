package com.herasber.TechnicalTest.platform.professional.application.internal.commandservices;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import com.herasber.TechnicalTest.platform.professional.domain.services.ProfessionalSourceQueryService;
import com.herasber.TechnicalTest.platform.professional.infrastructure.persistance.jpa.ProfessionalSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProfessionalSourceQueryServiceImpl implements ProfessionalSourceQueryService {

    private final ProfessionalSourceRepository repository;

    @Override
    public Optional<ProfessionalSource> handleGetById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProfessionalSource> handleGetAll() {
        return repository.findAll();
    }

    @Override
    public List<ProfessionalSource> handleGetAllByCity(String cityName) {
        return repository.findAllByLocation_CityName(cityName);
    }

    @Override
    public List<ProfessionalSource> handleGetAllByDistrict(String districtName) {
        return repository.findAllByLocation_DistrictName(districtName);
    }
}