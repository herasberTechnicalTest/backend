package com.herasber.platform.professional.domain.services;

import com.herasber.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;

import java.util.Optional;

public interface ProfessionalSourceCommandService {
    com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource handle(CreateProfessionalSourceCommand command);

}
