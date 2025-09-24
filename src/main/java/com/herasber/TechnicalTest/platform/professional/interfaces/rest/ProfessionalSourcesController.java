package com.herasber.TechnicalTest.platform.professional.interfaces.rest;

import com.herasber.TechnicalTest.platform.professional.domain.model.agreggates.ProfessionalSource;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.CreateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.model.commands.UpdateProfessionalSourceCommand;
import com.herasber.TechnicalTest.platform.professional.domain.services.ProfessionalSourceCommandService;
import com.herasber.TechnicalTest.platform.professional.domain.services.ProfessionalSourceQueryService;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources.CreateProfessionalSourceResource;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources.ProfessionalSourceResource;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.resources.UpdateProfessionalSourceResource;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.transform.CreateProfessionalSourceCommandFromResourceAssembler;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.transform.ProfessionalSourceResourceFromEntityAssembler;
import com.herasber.TechnicalTest.platform.professional.interfaces.rest.transform.UpdateProfessionalSourceCommandFromResourceAssembler;
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

    @GetMapping
    public List<ProfessionalSourceResource> getAll(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "district", required = false) String district) {

        List<ProfessionalSource> list;

        if (city != null && !city.isBlank() && district != null && !district.isBlank()) {
            list = queryService.handleGetAllByCity(city).stream()
                    .filter(p -> p.getLocation() != null
                            && district.equalsIgnoreCase(p.getLocation().getDistrictName()))
                    .toList();
        } else if (city != null && !city.isBlank()) {
            list = queryService.handleGetAllByCity(city);
        } else if (district != null && !district.isBlank()) {
            list = queryService.handleGetAllByDistrict(district);
        } else {
            list = queryService.handleGetAll();
        }

        return list.stream()
                .map(ProfessionalSourceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalSourceResource> getById(@PathVariable Long id) {
        return queryService.handleGetById(id)
                .map(ProfessionalSourceResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfessionalSourceResource> create(
            @Valid @RequestBody CreateProfessionalSourceResource body) {

        CreateProfessionalSourceCommand cmd =
                CreateProfessionalSourceCommandFromResourceAssembler.toCommandFromResource(body);

        ProfessionalSource saved = commandService.handle(cmd);
        var res = ProfessionalSourceResourceFromEntityAssembler.toResourceFromEntity(saved);

        return ResponseEntity.created(URI.create("/api/v1/professional/" + saved.getId())).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalSourceResource> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProfessionalSourceResource body) {

        if (!id.equals(body.id())) return ResponseEntity.badRequest().build();

        UpdateProfessionalSourceCommand cmd =
                UpdateProfessionalSourceCommandFromResourceAssembler.toCommandFromResource(body);

        return commandService.update(cmd)
                .map(ProfessionalSourceResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}