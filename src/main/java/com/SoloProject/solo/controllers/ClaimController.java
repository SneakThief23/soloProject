package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Claim;
import com.SoloProject.solo.models.User;
import com.SoloProject.solo.service.ClaimService;
import com.SoloProject.solo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/claims")
public class ClaimController{

    private final ClaimService claimService;
    private final UserService userService;

    public ClaimController(ClaimService claimService, UserService userService) {
        this.claimService = claimService;
        this.userService = userService;
    }

    @GetMapping
    public List<Claim> getAllClaim(UUID id) {
        return claimService.getAllClaim();
    }

    @GetMapping("/user/{id}")
    public List<Claim> getClaimByUser(@PathVariable("id") UUID id){
        return claimService.getClaimByMemberId(id);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Claim>> getUserClaims(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }

        List<Claim> claims = claimService.getClaimsForUser(userDetails.getUsername());
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<Claim>> getClaimsForCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }

        List<Claim> claims = claimService.getClaimsForUser(userDetails.getUsername());
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return ResponseEntity.status(401).build();
        return userService.findByEmail(userDetails.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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