package com.herasber.TechnicalTest.platform.professional.application.internal.commandservices;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.services.ProfessionalSourceCommandService;
import com.herasber.TechnicalTest.platform.professional.infrastructure.persistance.jpa.ProfessionalSourceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ProfessionalSourceCommandServiceImpl implements ProfessionalSourceCommandService {

    private final ProfessionalSourceRepository repo;

    @Override
    public ProfessionalSource handle(CreateProfessionalSourceCommand cmd) {
        String norm = cmd.email().trim().toLowerCase();
        if (repo.existsByEmailNorm(norm)) throw new IllegalArgumentException("Email already in use");

        var agg = ProfessionalSource.from(cmd);
        try {
            return repo.save(agg);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Email already in use");
        }
    }

    @Override
    public Optional<ProfessionalSource> update(UpdateProfessionalSourceCommand cmd) {
        String norm = cmd.email().trim().toLowerCase();
        return repo.findById(cmd.id()).map(p -> {
            if (!p.getEmail().equalsIgnoreCase(cmd.email()) && repo.existsByEmailNorm(norm)) {
                throw new IllegalArgumentException("Email already in use");
            }
            p.apply(cmd);
            return repo.save(p);
        });
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<String> getGallery(Long professionalId) {
        Optional<ProfessionalSource> professionalSource = repo.findById(professionalId);
        return professionalSource.map(ProfessionalSource::getGallery).orElse(null);
    }

    @Override
    public List<String> updateGallery(Long professionalId, List<String> gallery) {
        Optional<ProfessionalSource> professionalSource = repo.findById(professionalId);
        if (professionalSource.isPresent()) {
            ProfessionalSource source = professionalSource.get();
            source.setGallery(gallery);  // Asignamos la nueva galería al profesional
            repo.save(source);  // Guardamos los cambios
            return source.getGallery();  // Devolvemos la galería actualizada
        }
        return null;
    }

    @Override
    public boolean deleteImageFromGallery(Long professionalId, String imageUrl) {
        Optional<ProfessionalSource> professionalSource = repo.findById(professionalId);
        if (professionalSource.isPresent()) {
            ProfessionalSource source = professionalSource.get();
            boolean removed = source.getGallery().remove(imageUrl);
            if (removed) {
                repo.save(source);  // Guardamos los cambios
                return true;
            }
        }
        return false;
    }

}
