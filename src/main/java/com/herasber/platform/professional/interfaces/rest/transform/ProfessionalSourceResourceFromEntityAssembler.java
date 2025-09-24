package com.herasber.platform.professional.interfaces.rest.transform;

import com.herasber.platform.professional.interfaces.rest.resources.ProfessionalSourceResource;

public class ProfessionalSourceResourceFromEntityAssembler {
    public static ProfessionalSourceResource toResourceFromEntity(com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource e){
        var maps = e.buildMapsLink();
        var wa   = e.buildWhatsappLink(null);
        return new ProfessionalSourceResource(
                e.getId(),
                e.getFullName(),
                e.getPhone(),
                e.getServicesDescription(),
                e.getPhotoUrl(),
                e.getGallery(),
                e.getRate(),
                e.getCurrency(),
                e.getLocation().getCityName(),
                e.getLocation().getDistrictName(),
                e.buildMapsLink(),
                e.buildWhatsappLink(null)
        );
    }
}
