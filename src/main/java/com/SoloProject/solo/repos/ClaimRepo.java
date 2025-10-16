package com.SoloProject.solo.repos;

import com.SoloProject.solo.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface ClaimRepo extends JpaRepository<Claim, UUID>{
    List<Claim> findByMemberId(UUID memberId);
}
