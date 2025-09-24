package com.herasber.platform.professional.interfaces.rest;

import com.herasber.platform.professional.domain.model.aggregates.ProfessionalSource;
import com.herasber.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.platform.professional.domain.services.ProfessionalSourceCommandService;
import com.herasber.platform.professional.domain.services.ProfessionalSourceQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/professional")
@RequiredArgsConstructor
public class ProfessionalSourcesController {

    private final ProfessionalSourceCommandService commandService;
    private final ProfessionalSourceQueryService queryService;

    @PostMapping
    public ResponseEntity<ProfessionalSource> create(@Valid @RequestBody CreateProfessionalSourceCommand cmd) {
        ProfessionalSource saved = commandService.handle(cmd);
        return ResponseEntity
                .created(URI.create("/api/v1/professional-sources/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalSource> getById(@PathVariable Long id) {
        return queryService.handleGetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = "city")
    public List<ProfessionalSource> getByCity(@RequestParam("city") String city) {
        return queryService.handleGetAllByCity(city);
    }

    @GetMapping(params = "district")
    public List<ProfessionalSource> getByDistrict(@RequestParam("district") String district) {
        return queryService.handleGetAllByDistrict(district);
    }
}
