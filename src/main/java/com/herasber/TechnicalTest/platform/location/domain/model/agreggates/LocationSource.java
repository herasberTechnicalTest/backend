package com.herasber.TechnicalTest.platform.location.domain.model.agreggates;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
public class LocationSource {

    @Column(nullable = false) @NotBlank private String countryName;
    @Column(nullable = false) @NotBlank private String cityName;
    @Column(nullable = false) @NotBlank private String districtName;

    private String mapsUrl;

    public LocationSource(String countryName, String cityName, String districtName, String ignoredMapsUrl) {
        this.countryName  = countryName.trim();
        this.cityName     = cityName.trim();
        this.districtName = districtName.trim();
        this.mapsUrl = null;
    }

    public void ensureMapsUrl() {
        String q = (districtName + ", " + cityName + ", " + countryName)
                .replaceAll("\\s+", " ").trim();
        this.mapsUrl = "https://www.google.com/maps/search/?api=1&query=" +
                URLEncoder.encode(q, StandardCharsets.UTF_8);
    }

    public String getOrBuildMapsUrl() {
        if (this.mapsUrl == null || this.mapsUrl.isBlank()) ensureMapsUrl();
        return this.mapsUrl;
    }

    @Override public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof LocationSource that)) return false;
        return Objects.equals(countryName, that.countryName)
                && Objects.equals(cityName, that.cityName)
                && Objects.equals(districtName, that.districtName)
                && Objects.equals(mapsUrl, that.mapsUrl);
    }
    @Override public int hashCode(){ return Objects.hash(countryName, cityName, districtName, mapsUrl); }
}