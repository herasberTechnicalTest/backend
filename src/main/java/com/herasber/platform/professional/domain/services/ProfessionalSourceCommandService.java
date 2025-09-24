package com.herasber.platform.professional.domain.services;

import com.herasber.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;

import java.util.Optional;


public interface ProfessionalSourceCommandService {
    com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource handle(CreateProfessionalSourceCommand command);
    Optional<com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource> update(Long id, UpdateProfessionalSourceCommand command);
    boolean delete(Long id);
}
