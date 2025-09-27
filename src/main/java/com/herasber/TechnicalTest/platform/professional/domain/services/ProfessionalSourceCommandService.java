package com.herasber.TechnicalTest.platform.professional.domain.services;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;

import java.util.List;
import java.util.Optional;


public interface ProfessionalSourceCommandService {
    ProfessionalSource handle(CreateProfessionalSourceCommand command);
    Optional<ProfessionalSource> update(UpdateProfessionalSourceCommand command); // PUT
    void delete(Long id);
    List<String> getGallery(Long professionalId);
    List<String> updateGallery(Long professionalId, List<String> gallery);
    boolean deleteImageFromGallery(Long professionalId, String imageUrl);

}
