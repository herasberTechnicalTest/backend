package com.herasber.TechnicalTest.platform.professional.interfaces.rest.transform;

import com.herasber.TechnicalTest.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources.UpdateProfessionalSourceResource;

public class UpdateProfessionalSourceCommandFromResourceAssembler {
    public static UpdateProfessionalSourceCommand toCommandFromResource(UpdateProfessionalSourceResource r) {
        return new UpdateProfessionalSourceCommand(
                r.id(),
                r.fullName(),
                r.email(),
                r.password(),
                r.phone(),
                r.servicesDescription(),
                r.photoUrl(),
                r.gallery(),
                r.rate(),
                r.currency()
        );
    }
}