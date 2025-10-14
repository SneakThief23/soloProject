package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProviderRepo extends JpaRepository<Provider, UUID> {
}
