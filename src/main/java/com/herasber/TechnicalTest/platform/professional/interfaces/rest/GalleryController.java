package com.herasber.TechnicalTest.platform.professional.interfaces.rest;

import com.herasber.TechnicalTest.platform.professional.domain.services.ProfessionalSourceCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professionals")
public class GalleryController {
    private final ProfessionalSourceCommandService commandService;

    public GalleryController(ProfessionalSourceCommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/{id}/gallery")
    public ResponseEntity<List<String>> getGallery(@PathVariable Long id) {
        List<String> gallery = commandService.getGallery(id);
        if (gallery != null) {
            return ResponseEntity.ok(gallery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/gallery")
    public ResponseEntity<List<String>> updateGallery(
            @PathVariable Long id,
            @RequestBody List<String> updatedGallery) {

        List<String> newGallery = commandService.updateGallery(id, updatedGallery);
        if (newGallery != null) {
            return ResponseEntity.ok(newGallery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/gallery")
    public ResponseEntity<Void> deleteImage(
            @PathVariable Long id,
            @RequestBody String imageUrl) {

        boolean deleted = commandService.deleteImageFromGallery(id, imageUrl);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
