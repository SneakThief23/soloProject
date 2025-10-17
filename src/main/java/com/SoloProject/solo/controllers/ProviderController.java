package com.SoloProject.solo.controllers;

import com.SoloProject.solo.models.Provider;
import com.SoloProject.solo.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/provider")
public class ProviderController{

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public List<Provider> getAllProvider() {
        return providerService.getAllProvider();
    }

    @GetMapping("/user/{id}")
    public List<Provider> getProviderByUser(@PathVariable("id") UUID id){
        return providerService.getProviderByMemberId(id);
    }

    @PostMapping
    public Provider createProvider(@RequestBody Provider provider) {
        return providerService.createProvider(provider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable UUID id, @RequestBody Provider provider) {
        Optional<Provider> updated = providerService.updateProvider(id, provider);
        return updated
                .map(acc -> ResponseEntity.ok(acc))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable UUID id) {
        boolean deleted = providerService.deleteProvider(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
