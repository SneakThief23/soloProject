package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Claim;
import com.SoloProject.solo.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/claim")
public class ClaimController{

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping
    public List<Claim> getAllClaim(UUID id) {
        return claimService.getAllClaim();
    }

    @GetMapping("/user/{id}")
    public List<Claim> getClaimByUser(@PathVariable("id") UUID id){
        return claimService.getClaimByMemberId(id);
    }

    @PostMapping
    public Claim createClaim(@RequestBody Claim claim) {
        return claimService.createClaim(claim);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable UUID id, @RequestBody Claim claim) {
        Optional<Claim> updated = claimService.updateClaim(id, claim);
        return updated
                .map(acc -> ResponseEntity.ok(acc))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable UUID id) {
        boolean deleted = claimService.deleteClaim(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}