package com.herasber.TechnicalTest.platform.professional.domain.model.agreggates;

import com.herasber.TechnicalTest.platform.location.domain.model.agreggates.LocationSource;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "professional_sources")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ProfessionalSource extends AbstractAggregateRoot<ProfessionalSource> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false) @Getter @NotBlank
    private String fullName;

    @Column(nullable = false) @Getter @NotBlank
    private String phone;

    @Column(nullable = false, length = 5000) @Getter @NotBlank
    private String servicesDescription;

    @Getter
    private String photoUrl;

    @ElementCollection
    @CollectionTable(name = "professional_source_gallery", joinColumns = @JoinColumn(name = "professional_id"))
    @Column(name = "image_url", length = 1024)
    @Getter
    private List<String> gallery = new ArrayList<>();

    @Getter
    private BigDecimal rate;

    @Getter
    private String currency;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "countryName",  column = @Column(name = "country_name",  nullable = false)),
            @AttributeOverride(name = "cityName",     column = @Column(name = "city_name",     nullable = false)),
            @AttributeOverride(name = "districtName", column = @Column(name = "district_name", nullable = false)),
            @AttributeOverride(name = "mapsUrl",      column = @Column(name = "maps_url"))
    })
    @Getter
    private LocationSource location;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected ProfessionalSource(CreateProfessionalSourceCommand command) {
        this.fullName = command.fullName();
        this.phone = command.phone();
        this.servicesDescription = command.servicesDescription();
        this.photoUrl = command.photoUrl();

        if (command.gallery() != null) this.gallery = new ArrayList<>(command.gallery());
        this.rate = command.rate();
        this.currency = command.currency();

        this.location = new LocationSource(
                command.countryName(),
                command.cityName(),
                command.districtName(),
                null
        );
        ensureDerivedFields();
    }

    public static ProfessionalSource from(CreateProfessionalSourceCommand c) {
        return new ProfessionalSource(c);
    }

    public void apply(UpdateProfessionalSourceCommand command) {
        this.fullName = command.fullName();
        this.phone = command.phone();
        this.servicesDescription = command.servicesDescription();
        this.photoUrl = command.photoUrl();

        this.gallery = (command.gallery() != null) ? new ArrayList<>(command.gallery()) : new ArrayList<>();
        this.rate = command.rate();
        this.currency = command.currency();

        this.location = new LocationSource(
                command.countryName(),
                command.cityName(),
                command.districtName(),
                null
        );
        ensureDerivedFields();
    }

    public String buildWhatsappLink(String preset) {
        String msg = (preset == null || preset.isBlank()) ? "Hola, vi tu perfil en la plataforma." : preset;
        String enc = java.net.URLEncoder.encode(msg, StandardCharsets.UTF_8);
        return "https://api.whatsapp.com/send?phone=" + phone + "&text=" + enc;
    }

    public String getMapsUrl() {
        return (location != null) ? location.getOrBuildMapsUrl() : null;
    }

    @PrePersist
    @PreUpdate
    private void ensureDerivedFields() {
        if (location != null) location.ensureMapsUrl();
    }
}