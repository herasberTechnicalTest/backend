package com.herasber.TechnicalTest.platform.Auth.interfaces.rest;

import com.herasber.TechnicalTest.platform.Auth.domain.commands.LoginRequest;
import com.herasber.TechnicalTest.platform.Auth.domain.commands.LoginResponse;
import com.herasber.TechnicalTest.platform.Auth.services.SimpleTokenService;
import com.herasber.TechnicalTest.platform.professional.infrastructure.persistance.jpa.ProfessionalSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ProfessionalSourceRepository repo;
    private final SimpleTokenService tokens;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
        var prof = repo.findByEmail(req.email().trim().toLowerCase())
                .orElse(null);
        if (prof == null || !prof.matchesPassword(req.password())) {
            return ResponseEntity.status(401).build();
        }
        var token = tokens.issue(prof.getId());
        return ResponseEntity.ok(new LoginResponse(token, prof.getId(), prof.getFullName(), prof.getEmail()));
    }
}

