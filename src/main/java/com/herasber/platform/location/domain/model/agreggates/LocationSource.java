package com.herasber.platform.location.domain.model.aggregates;

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
    @Column(nullable = false) @NotBlank private String cityName;
    @Column(nullable = false) @NotBlank private String districtName;
    private String mapsUrl;

    public LocationSource(String cityName, String districtName, String mapsUrl) {
        this.cityName = cityName.trim();
        this.districtName = districtName.trim();
        this.mapsUrl = (mapsUrl == null || mapsUrl.isBlank()) ? null : mapsUrl.trim();
    }
    public String buildMapsUrl() { return buildMapsUrl(null); }
    public String buildMapsUrl(String countryName) {
        if (mapsUrl != null && !mapsUrl.isBlank()) return mapsUrl;
        String q = (districtName + ", " + cityName + (countryName != null && !countryName.isBlank() ? ", " + countryName : "")).replaceAll("\\s+"," ").trim();
        return "https://www.google.com/maps/search/?api=1&query=" + URLEncoder.encode(q, StandardCharsets.UTF_8);
    }
    @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof LocationSource that)) return false;
        return Objects.equals(cityName, that.cityName) && Objects.equals(districtName, that.districtName) && Objects.equals(mapsUrl, that.mapsUrl);}
    @Override public int hashCode(){ return Objects.hash(cityName, districtName, mapsUrl); }
}