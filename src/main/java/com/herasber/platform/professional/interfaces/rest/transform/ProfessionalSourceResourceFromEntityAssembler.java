package com.herasber.platform.professional.interfaces.rest.transform;

import com.herasber.platform.professional.interfaces.rest.resources.ProfessionalSourceResource;

public class ProfessionalSourceResourceFromEntityAssembler {
    public static ProfessionalSourceResource toResourceFromEntity(com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource e){
        var loc = e.getLocation();
        String country   = (loc != null) ? loc.getCountryName()  : null;
        String city      = (loc != null) ? loc.getCityName()     : null;
        String district  = (loc != null) ? loc.getDistrictName() : null;

        return new ProfessionalSourceResource(
                e.getId(),
                e.getFullName(),
                e.getPhone(),
                e.getServicesDescription(),
                e.getPhotoUrl(),
                e.getGallery(),
                e.getRate(),
                e.getCurrency(),
                country,
                city,
                district,
                e.getMapsUrl(),
                e.buildWhatsappLink(null)
        );
    }
}
