package com.herasber.platform.professional.interfaces.rest;

import com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource;
import com.herasber.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;
import com.herasber.platform.professional.domain.services.ProfessionalSourceCommandService;
import com.herasber.platform.professional.domain.services.ProfessionalSourceQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/v1/professional")
@RequiredArgsConstructor
public class ProfessionalSourcesController {

    private final ProfessionalSourceCommandService commandService;
    private final ProfessionalSourceQueryService queryService;

    @PostMapping
    public ResponseEntity<ProfessionalSource> create(@Valid @RequestBody CreateProfessionalSourceCommand cmd) {
        var saved = commandService.handle(cmd);
        return ResponseEntity.created(URI.create("/api/v1/professional/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalSource> getById(@PathVariable Long id) {
        return queryService.handleGetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "district", required = false) String district) {

        if (city == null && district == null) {
            return ResponseEntity.ok(queryService.handleGetAll());
        }
        if (city != null && district != null) {
            return ResponseEntity.badRequest().body("Use solo uno de los filtros: city o district.");
        }
        if (city != null) {
            return ResponseEntity.ok(queryService.handleGetAllByCity(city));
        } else {
            return ResponseEntity.ok(queryService.handleGetAllByDistrict(district));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalSource> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProfessionalSourceCommand cmd) {
        return commandService.update(id, cmd)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = commandService.delete(id);
        return removed ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}

