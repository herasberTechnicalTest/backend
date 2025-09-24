package com.herasber.TechnicalTest.platform.professional.domain.services;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;

import java.util.Optional;


public interface ProfessionalSourceCommandService {
    ProfessionalSource handle(CreateProfessionalSourceCommand command);
    Optional<ProfessionalSource> update(UpdateProfessionalSourceCommand command); // PUT
    void delete(Long id);
}
