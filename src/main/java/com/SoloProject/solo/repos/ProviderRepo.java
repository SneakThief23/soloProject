package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProviderRepo extends JpaRepository<Provider, UUID> {
    List<Provider> findByMemberId(UUID memberId);
}
