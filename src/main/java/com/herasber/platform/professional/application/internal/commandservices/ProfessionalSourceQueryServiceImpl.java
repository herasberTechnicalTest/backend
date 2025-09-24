package com.herasber.platform.professional.application.internal.commandservices;

import com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource;
import com.herasber.platform.professional.domain.services.ProfessionalSourceQueryService;
import com.herasber.platform.professional.infrastructure.persistance.jpa.ProfessionalSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProfessionalSourceQueryServiceImpl implements ProfessionalSourceQueryService {

    private final ProfessionalSourceRepository professionalSourceRepository;

    @Override
    public Optional<ProfessionalSource> handleGetById(Long id) {
        return professionalSourceRepository.findById(id);
    }

    @Override
    public List<ProfessionalSource> handleGetAllByCity(String cityName) {
        return professionalSourceRepository.findAllByLocation_CityName(cityName);
    }

    @Override
    public List<ProfessionalSource> handleGetAllByDistrict(String districtName) {
        return professionalSourceRepository.findAllByLocation_DistrictName(districtName);
    }
}