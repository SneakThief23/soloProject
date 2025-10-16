package com.SoloProject.solo.service;

import com.SoloProject.solo.models.Claim;
import com.SoloProject.solo.models.Provider;
import com.SoloProject.solo.repos.ProviderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService {

    private final ProviderRepo providerRepo;

    public ProviderService(ProviderRepo providerRepo){
        this.providerRepo = providerRepo;
    }

    public List<Provider> getAllProvider() {
        return providerRepo.findAll();
    }

    public List<Provider> getProviderByMemberId(UUID memberId) {
        return providerRepo.findByMemberId(memberId);
    }

    public Provider createProvider(Provider provider) {
        return providerRepo.save(provider);
    }
    // Update existing Provider
    public Optional<Provider> updateProvider(UUID id, Provider newData) {
        return providerRepo.findById(id).map(existing -> {
            if (newData.getName () != null)existing.setName(newData.getName());
            if (newData.getSpecialty () != null)existing.setSpecialty(newData.getSpecialty());
            if (newData.getAddress () != null)existing.setAddress(newData.getAddress());
            if (newData.getPhone () != null)existing.setPhone(newData.getPhone());
            return providerRepo.save(existing);
        });
    }
    public boolean deleteProvider(UUID id) {
        if (providerRepo.existsById(id)) {
            providerRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
