package com.herasber.TechnicalTest.platform.professional.interfaces.rest.transform;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources.ProfessionalSourceResource;

public class ProfessionalSourceResourceFromEntityAssembler {
    public static ProfessionalSourceResource toResourceFromEntity(ProfessionalSource e){
        var loc = e.getLocation();
        String country   = (loc != null) ? loc.getCountryName()  : null;
        String city      = (loc != null) ? loc.getCityName()     : null;
        String district  = (loc != null) ? loc.getDistrictName() : null;

        return new ProfessionalSourceResource(
                e.getId(),
                e.getFullName(),
                e.getEmail(),
                e.getPhone(),
                e.getServicesDescription(),
                e.getPhotoUrl(),
                e.getGallery(),
                e.getRate(),
                e.getCurrency(),
                loc != null ? loc.getCountryName()  : null,
                loc != null ? loc.getCityName()     : null,
                loc != null ? loc.getDistrictName() : null,
                e.getMapsUrl(),
                e.buildWhatsappLink(null)
        );
    }
}
