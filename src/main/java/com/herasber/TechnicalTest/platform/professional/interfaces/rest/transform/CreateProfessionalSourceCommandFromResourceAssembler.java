package com.herasber.TechnicalTest.platform.professional.interfaces.rest.transform;

import com.herasber.TechnicalTest.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources.CreateProfessionalSourceResource;

public class CreateProfessionalSourceCommandFromResourceAssembler {
    public static CreateProfessionalSourceCommand toCommandFromResource(CreateProfessionalSourceResource r){
        return new CreateProfessionalSourceCommand(
                r.fullName(),
                r.email(),
                r.password(),
                r.phone(),
                r.servicesDescription(),
                r.photoUrl(),
                r.gallery(),
                r.rate(),
                r.currency(),
                r.countryName(),
                r.cityName(),
                r.districtName()
        );
    }
}
