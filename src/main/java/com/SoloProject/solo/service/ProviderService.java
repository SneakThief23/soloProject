package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Provider;
import com.SoloProject.solo.repos.ProviderRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService {

    private final ProviderRepo providerRepo;

    public ProviderService(ProviderRepo providerRepo){
        this.providerRepo = providerRepo;
    }
    // Update existing Provider
    public Optional<Provider> updateProvider(UUID id, Provider newData) {
        return providerRepo.findById(id).map(existing -> {
            existing.setId(newData.getId());
            existing.setName(newData.getName());
            existing.setSpecialty(newData.getSpecialty());
            existing.setAddress(newData.getAddress());
            existing.setPhone(newData.getPhone());
            return providerRepo.save(existing);
        });
    }
}
