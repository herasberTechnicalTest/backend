package com.herasber.platform.professional.application.internal.commandservices;

import com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource;
import com.herasber.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.platform.professional.domain.services.ProfessionalSourceCommandService;
import com.herasber.platform.professional.infrastructure.persistance.jpa.ProfessionalSourceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class ProfessionalSourceCommandServiceImpl implements ProfessionalSourceCommandService {

    private final ProfessionalSourceRepository repository;
    @Override
    public ProfessionalSource handle(CreateProfessionalSourceCommand command) {
        ProfessionalSource aggregate = ProfessionalSource.from(command); // ← aquí
        return repository.save(aggregate);
    }
}
