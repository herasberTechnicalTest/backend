package com.herasber.TechnicalTest.platform.professional.domain.model.agreggates;

import com.herasber.TechnicalTest.platform.location.domain.model.agreggates.LocationSource;
import com.herasber.TechnicalTest.platform.password.domain.model.agreggates.Passwords;
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
    @Lob
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;

    @ElementCollection
    @Getter
    @CollectionTable(name = "professional_gallery", joinColumns = @JoinColumn(name = "professional_id"))
    @Column(name = "image_data", columnDefinition = "TEXT")
    private List<String> gallery = new ArrayList<>();

    @Getter
    private BigDecimal rate;

    @Getter
    private String currency;

    @Column(nullable = false, unique = true) @Getter
    private String email;

    @Column(name = "email_norm", nullable = false, length = 320, unique = true)
    private String emailNorm;

    @Column(nullable = false, name = "password_hash")
    private String passwordHash;

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

        setEmail(command.email());
        this.passwordHash = Passwords.hash(command.password());

        if (command.gallery() != null) this.gallery = new ArrayList<>(command.gallery());
        this.rate = command.rate();
        this.currency = command.currency();

        this.location = new LocationSource(
                command.countryName(),
                command.cityName(),
                command.districtName(),
                null
        );
    }

    public static ProfessionalSource from(CreateProfessionalSourceCommand c) {
        return new ProfessionalSource(c);
    }

    public String getPasswordHash() { return passwordHash; }

    public void apply(UpdateProfessionalSourceCommand command) {
        this.fullName = command.fullName();
        this.phone = command.phone();
        this.servicesDescription = command.servicesDescription();
        this.photoUrl = command.photoUrl();

        if (command.email() != null && !command.email().isBlank()) {
            setEmail(command.email());
        }

        if (command.password() != null && !command.password().isBlank()) {
            changePassword(command.password());
        }

        this.gallery = (command.gallery() != null) ? new ArrayList<>(command.gallery()) : new ArrayList<>();
        this.rate = command.rate();
        this.currency = command.currency();


    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be null/blank");
        this.email = email.trim();
        this.emailNorm = this.email.toLowerCase();
    }

    public boolean matchesPassword(String raw) {
        return Passwords.matches(raw, this.passwordHash);
    }

    public void changePassword(String newRawPassword) {
        if (newRawPassword == null || newRawPassword.isBlank()) {
            throw new IllegalArgumentException("New password cannot be null or blank");
        }
        this.passwordHash = Passwords.hash(newRawPassword);
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
    private void beforeSave() {
        if (email != null) {
            email = email.trim();
            emailNorm = email.toLowerCase();
        }
        if (location != null) {
            location.ensureMapsUrl();
        }
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }
}