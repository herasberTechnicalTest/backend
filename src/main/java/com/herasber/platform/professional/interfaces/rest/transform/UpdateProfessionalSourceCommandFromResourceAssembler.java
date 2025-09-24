package com.herasber.platform.professional.interfaces.rest.transform;

import com.herasber.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;
import com.herasber.platform.professional.interfaces.rest.resources.UpdateProfessionalSourceResource;

public class UpdateProfessionalSourceCommandFromResourceAssembler {
    public static UpdateProfessionalSourceCommand toCommandFromResource(UpdateProfessionalSourceResource r) {
        return new UpdateProfessionalSourceCommand(
                r.id(),
                r.fullName(),
                r.phone(),
                r.servicesDescription(),
                r.photoUrl(),
                r.gallery(),
                r.rate(),
                r.currency(),
                r.cityName(),
                r.districtName(),
                r.mapsUrl()
        );
    }
}